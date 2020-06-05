package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.missionedappdev.missoned.utility.StudentAPI;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    private static final int SPLASH_SCREEN_TIME_OUT=1500;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // to see if user is already logged in or not
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser=firebaseAuth.getCurrentUser();
                if(currentUser!=null)
                {
                    // user is already logged in, take him to HomeScreen
                    currentUser=firebaseAuth.getCurrentUser();
                    final String UserID=currentUser.getUid();

                    collectionReference
                            .whereEqualTo("UserID",UserID)
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                    if(e!=null) {
                                        e.printStackTrace(); // exception thrown
                                    }
                                    String name;
                                    if(!queryDocumentSnapshots.isEmpty()) {
                                        for(QueryDocumentSnapshot snapshot:queryDocumentSnapshots) {
                                            StudentAPI studentAPI=StudentAPI.getInstance();
                                            studentAPI.setUserID(UserID);
                                            studentAPI.setUsername(snapshot.getString("Username"));

                                            Intent intent=new Intent(MainActivity.this, HomeScreen.class);
                                            intent.putExtra("USERNAME",studentAPI.getUsername());
                                            Log.d("Status","Logged in successfully");
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }
                            });

                }
                else
                {
                    // user is using app for first time, walk him through splash screens
                    Log.d("Status","User is logged out");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this,onboarding.class);
                            startActivity(i);
                            finish();
                        }
                    },SPLASH_SCREEN_TIME_OUT);
                }
            }
        };

      //startActivity(new Intent(MainActivity.this,HomeScreen.class));
      // Intent i = new Intent(MainActivity.this,onboarding.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser=firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(firebaseAuth!=null) firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

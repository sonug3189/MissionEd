package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.missionedappdev.missoned.utility.StudentAPI;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnSignIn; TextView btnSignUp;
    TextView forgot_password;

    Button testbutn;

    public Intent intent1;

    // firebase instances
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    // connection to firestore
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference=db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * @author user : soumitri2001
         * this activity is for logging in an existing user
         * etEmail and etPassword will have the entered respective texts, email and pass are string equivalents of the same
         * btnSignIn is the button for signing in. The user needs to be authenticated before logging him in.
         */

        testbutn=findViewById(R.id.testbtn);
        testbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(LoginActivity.this,HomeScreen.class);
                intent1.putExtra("USERNAME","Debugger");
                startActivity(intent1);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnSignIn=findViewById(R.id.btnSignIn);
        forgot_password=findViewById(R.id.forgot_pass);
        btnSignUp=findViewById(R.id.btnSignUpPage);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields to sign in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUserWithEmailPass(email,pass);

                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, signup_option.class));
                finish();
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,forgot_password.class);
                startActivity(intent);
            }
        });

    }

    private void loginUserWithEmailPass(final String email, String pass) {

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        currentUser=firebaseAuth.getCurrentUser();
                        assert currentUser!=null;
                        if(!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Invalid credentials, try again !", Toast.LENGTH_LONG).show();
                        } else {
                            final String UserID = currentUser.getUid();
                            collectionReference
                                    .whereEqualTo("UserID", UserID)
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                                            @Nullable FirebaseFirestoreException e) {

                                            if (e != null) {
                                                e.printStackTrace();
                                            }
                                            assert queryDocumentSnapshots != null;
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                Toast.makeText(LoginActivity.this, "Logged in !", Toast.LENGTH_SHORT).show();
                                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                                                    StudentAPI studentAPI = StudentAPI.getInstance();
                                                    studentAPI.setUsername(snapshot.getString("Username"));
                                                    studentAPI.setUserID(snapshot.getString("UserID"));

                                                    // go to the HomeScreen
                                                    Intent intent=new Intent(LoginActivity.this, HomeScreen.class);
                                                    intent.putExtra("EMAIL",email);
                                                    intent.putExtra("USERNAME",studentAPI.getUsername());
                                                    Log.d("Status","Logged in successfully");
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Authentication error, Please enter correct details", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}







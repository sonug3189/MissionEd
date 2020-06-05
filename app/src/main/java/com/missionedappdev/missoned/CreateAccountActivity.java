package com.missionedappdev.missoned;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    EditText etEmailNew,etPasswordNew,etUsernameNew, etClass,etMobile;
    Button btnCreateAcc; TextView btnLoginPage;
    Button btnAddImage; ImageView ivImage;
    Uri imageUri;

    // firebase instances
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    // firestore instances
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    private CollectionReference collectionReference=db.collection("Users");


    int STD_CLASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firebaseAuth=FirebaseAuth.getInstance();

        /**
         * @author user : soumitri2001
         * this activity is for creating account for a new user
         * uname, email and pass are string equivalents of the EditText fields
         * btnAddImage is button to choose image from gallery, ivImage is the ImageView object that will store the image (NEED ASSISTANCE IN THIS)
         * btnCreateAcc is the button for signing up. The user needs to be authenticated before signing him up.
         */

        etEmailNew=findViewById(R.id.etEmailNew);
        etPasswordNew=findViewById(R.id.etPasswordNew);
        etUsernameNew=findViewById(R.id.etUsernameNew);
        btnCreateAcc=findViewById(R.id.btnCreateAcc);
        etClass=findViewById(R.id.etClass);
        etMobile=findViewById(R.id.etPhoneNumber);
        ivImage=findViewById(R.id.profile_picture);
        btnLoginPage=findViewById(R.id.btnLoginPage);
        btnAddImage=findViewById(R.id.btnAddImage);

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    currentUser=firebaseAuth.getCurrentUser();
                    if(currentUser!=null) {
                        /* user logged in already */

                    } else {
                        // no user currently
                    }
            }
        };

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });



        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccountActivity.this,LoginActivity.class));
                finish();
            }
        });

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=etEmailNew.getText().toString().trim(),
                        pass=etPasswordNew.getText().toString().trim(),
                        uname=etUsernameNew.getText().toString().trim(),
                        mobile=etMobile.getText().toString().trim(),
                        stdClass=etClass.getText().toString().trim();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(uname)
                        && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(stdClass)) {

                    STD_CLASS = Integer.parseInt(stdClass);
                    if(STD_CLASS<8 || STD_CLASS>12) {
                        Toast.makeText(CreateAccountActivity.this, "Invalid Class entered !", Toast.LENGTH_SHORT).show();
                        etClass.setText(null);
                    }
                    else
                    {
                        /*
                         * authentication and adding user to database : code
                         */

                        createUserAccount(email,pass,uname,mobile,stdClass);

                    }

                }
                else {
                    Toast.makeText(CreateAccountActivity.this, "All fields are compulsory to be filled up", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void createUserAccount(final String email, String pass, final String uname, final String mobile, final String stdClass)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful())
                      {
                          /**
                           * validate the user and add him to the Users collection
                           * then take user to HomeScreen
                           */

                          currentUser=firebaseAuth.getCurrentUser();
                          assert currentUser != null;
                          String currentUserId=currentUser.getUid();
                          sendEmailVerification();

                          HashMap<String,String> userObj=new HashMap<>();
                          userObj.put("UserID",currentUserId);
                          userObj.put("Username",uname);
                          userObj.put("Mobile",mobile);
                          userObj.put("Standard",stdClass);
                          userObj.put("Email",email);

                          // save to firestore DB
                          collectionReference.add(userObj)
                                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                      @Override
                                      public void onSuccess(DocumentReference documentReference) {
                                          /* create new document */
                                          documentReference.get()
                                                  .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                      @Override
                                                      public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                          if(Objects.requireNonNull(task.getResult()).exists())
                                                          {
                                                              String name=task.getResult().getString("Username");
                                                              String email=task.getResult().getString("Email");

                                                              Intent intent=new Intent(CreateAccountActivity.this, LoginActivity.class);
//                                                              intent.putExtra("USERNAME",name);
//                                                              intent.putExtra("EMAIL",email);
//
//                                                              Log.d("Status","Signed up successfully");

                                                              startActivity(intent);
                                                              finish();

                                                          } else { /* something went wrong in authentication */
                                                          Log.d("Status","task.getResult() does not exist");}
                                                      }
                                                  });
                                      }
                                  })
                                  .addOnFailureListener(new OnFailureListener() {
                                      @Override
                                      public void onFailure(@NonNull Exception e) {
                                          /* failed to create account */
                                          Log.d("Status","Failed to add user object");
                                      }
                                  });


                      }
                      else { /*Something went wrong !*/
                          Log.d("Status","task was not successful");
                          try
                          {
                              throw task.getException();
                          }
                          catch (FirebaseAuthUserCollisionException existEmail)
                          {
                              //Log.d(TAG, "onComplete: exist_email");
                              Toast.makeText(CreateAccountActivity.this,"Email Already exist",Toast.LENGTH_LONG).show();

                          }
                          catch (Exception e)
                          {
                              Log.d("Status", "onComplete: " + e.getMessage());
                          }
                      }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Status","User auth and acc creation failed");
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser=firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 & resultCode==RESULT_OK) {
            if(data!=null) {
                imageUri = data.getData();
//                Picasso.(CreateAccountActivity.this).load(imageUri).noPlaceholder().centerCrop().fit()
//                        .into(ivImage);
//                ivImage.setImageURI(imageUri);

                Picasso.get()
                        .load(imageUri)
                        .noPlaceholder()
                        .fit()
                        .into(ivImage); btnAddImage.setVisibility(View.INVISIBLE);

                Log.d("Status" , "Profile picture added");
            }
        }
    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String email2 = firebaseUser.getEmail();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(CreateAccountActivity.this,"Successfully Registered and Email send !!",Toast.LENGTH_SHORT).show();
                        //firebaseAuth.signOut();
                    }
                    else{
                        Toast.makeText(CreateAccountActivity.this,"Verification mail not send ",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

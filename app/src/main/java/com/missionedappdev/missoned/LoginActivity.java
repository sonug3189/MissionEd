package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnSignIn; TextView btnSignUp;
    TextView forgot_password;
    private FirebaseAuth firebaseAuth;
    public FirebaseUser firebaseUser;
    public FirebaseDatabase firebaseDatabase;
    Button testbutn;
    Student student;
    String name1;
    public Intent intent1;

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
        testbutn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, profile.class);
                startActivity(intent);
            }
        }));



        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnSignIn=findViewById(R.id.btnSignIn);
        forgot_password=findViewById(R.id.forgot_pass);
        firebaseAuth = FirebaseAuth.getInstance();
        btnSignUp=findViewById(R.id.btnSignUpPage);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email=etEmail.getText().toString().trim();
                String pass=etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Please enter all fields to sign in", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this, "Login Successful   !!", Toast.LENGTH_SHORT).show();
                                 intent1 = new Intent(LoginActivity.this,HomeScreen.class);
                                 intent1.putExtra("Email",email);
                                 String id1 = FirebaseAuth.getInstance().getUid();
                                 intent1.putExtra("id",id1);
                                 startActivity(intent1);
                                 finish();
                            }
                            else{

                                Toast.makeText(LoginActivity.this, "Login failed !!", Toast.LENGTH_SHORT).show();
                                etEmail.setText("");
                                etPassword.setText("");
                            }
                        }
                    });

                    /*
                    * user authentication code : check if user exists or not and show toast messages accordingly
                    */

                    //Toast.makeText(LoginActivity.this, "Logged in successfully !", Toast.LENGTH_SHORT).show();
                    //Log.d("Status","Logged in");

                    /*startActivity(new Intent(LoginActivity.this, HomeScreen.class));
                    finish();*/
//                    Intent intent=new Intent(LoginActivity.this, HomeScreen.class);
//                    intent.putExtra("Email",email);
//                    startActivity(intent);
//                    finish();

                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, select_grade.class));
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
}

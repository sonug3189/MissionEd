package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    private EditText resetemail;
    private Button resetbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        resetbtn = findViewById(R.id.resetbtn);
        resetemail = findViewById(R.id.resetEmail);
        mAuth = FirebaseAuth.getInstance();
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=resetemail.getText().toString().trim();
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(forgot_password.this, "Please enter all fields to sign in", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(forgot_password.this,"Reset password mail sent",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(forgot_password.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(forgot_password.this,"Email does not exist",Toast.LENGTH_LONG).show();
                                        resetemail.setText("");
                                        FirebaseAuth.getInstance().signOut();
                                    }
                                }
                            });

                }
            }
        });

    }
}

package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class signup_option extends AppCompatActivity {

    private Button tutor, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_option);


        tutor = findViewById(R.id.button10);
        student = findViewById(R.id.button11);


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup_option.this, select_grade.class));
                finish();
            }
        });
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup_option.this, tutor_signup.class));
                finish();
            }
        });
    }


}

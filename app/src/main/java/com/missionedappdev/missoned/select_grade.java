package com.missionedappdev.missoned;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class select_grade extends AppCompatActivity {

    private Button jee,olumpiad,cbse,icse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grade);


        jee = findViewById(R.id.button7);
        olumpiad = findViewById(R.id.button8);
        cbse = findViewById(R.id.button);
        icse = findViewById(R.id.button9);


        jee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_grade.this, CreateAccountActivity.class));
            }
        });
        jee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_grade.this, CreateAccountActivity.class));
            }
        });
        cbse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_grade.this, CreateAccountActivity.class));
            }
        });
        icse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(select_grade.this, CreateAccountActivity.class));
            }
        });
    }
}

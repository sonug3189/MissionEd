package com.missionedappdev.missoned;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class activity_chapter extends AppCompatActivity {
    private static final String TAG = "activity_chapter";
    private ImageView btnAct1;
    private ImageView btnAct2;
    private ImageView btnAct3;

    private int chapIndex;
    private String subject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        chapIndex=getIntent().getIntExtra("CHAPTER_INDEX",0);
        subject=getIntent().getStringExtra("SUBJECT");

        Log.d(subject,Integer.toString(chapIndex));

        btnAct1 = findViewById(R.id.notes);
        btnAct2 = findViewById(R.id.quizoes);
        btnAct3 = findViewById(R.id.test);

        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","notes opened");
                Intent intent=new Intent(activity_chapter.this,activity_notes.class);
                intent.putExtra("CHAPTER_INDEX",chapIndex);
                intent.putExtra("SUBJECT",subject);
                startActivity(intent);
            }
        });

        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","quizoes opened");
                Intent intent=new Intent(activity_chapter.this,activity_quizoes.class);
                intent.putExtra("CHAPTER_INDEX",chapIndex);
                intent.putExtra("SUBJECT",subject);
                startActivity(intent);
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Step","test opened");
                Intent intent=new Intent(activity_chapter.this,activity_test.class);
                intent.putExtra("CHAPTER_INDEX",chapIndex);
                intent.putExtra("SUBJECT",subject);
                startActivity(intent);
            }
        });

    }

}

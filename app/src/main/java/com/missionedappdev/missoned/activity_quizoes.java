package com.missionedappdev.missoned;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class activity_quizoes extends AppCompatActivity {

    private TextView quizzoChapterIndex;
    private int chapIndex; private String subject;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizoes);

        chapIndex=getIntent().getIntExtra("CHAPTER_INDEX",0);
        subject=getIntent().getStringExtra("SUBJECT");
        Log.d("Fetch quizzoes for:",subject+": chapter"+(chapIndex+1));

        quizzoChapterIndex=findViewById(R.id.quizzoChapterIndex);
        quizzoChapterIndex.setText(subject+": chapter="+(chapIndex+1));
    }
}

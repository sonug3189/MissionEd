package com.missionedappdev.missoned;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class activity_test extends AppCompatActivity {

    private int chapIndex;
    private String subject;
    private TextView testChapterIndex;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        chapIndex = getIntent().getIntExtra("CHAPTER_INDEX", 0);
        subject = getIntent().getStringExtra("SUBJECT");
        Log.d("Fetch tests for:",subject+": chapter"+(chapIndex+1));

        testChapterIndex = findViewById(R.id.testChapterIndex);
        testChapterIndex.setText("Test for "+subject+": chapter="+(chapIndex+1));
    }
}

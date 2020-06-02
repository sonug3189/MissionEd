package com.missionedappdev.missoned;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class activity_notes extends AppCompatActivity {

    private int chapIndex;
    private String subject;
    private TextView notesChapterIndex;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        chapIndex=getIntent().getIntExtra("CHAPTER_INDEX",0);
        subject=getIntent().getStringExtra("SUBJECT");
        Log.d("Fetch notes for","chapter"+(chapIndex+1));

        notesChapterIndex=findViewById(R.id.notesChapterIndex);
        notesChapterIndex.setText(subject+": chapter="+(chapIndex+1));
    }
}

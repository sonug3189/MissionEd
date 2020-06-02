package com.missionedappdev.missoned;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class profile extends AppCompatActivity implements View.OnClickListener{


    Spinner spinner;
    ImageButton editbtn,editbtn1;
    TextView textView,seektext,phnotext,emailtext,gendertxt,citytxt,dobtxt;
    SeekBar seekBar;
    final String arr[] ={"CHOOSE YOUR GRADE","1st Grade","2nd Grade", "3rd Grade" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        spinner=(Spinner)findViewById(R.id.spin);
        editbtn =(ImageButton)findViewById(R.id.imgbtn);
        editbtn1=(ImageButton)findViewById(R.id.imgbtn1);
        textView = (TextView)findViewById(R.id.text);
        emailtext=(TextView)findViewById(R.id.emailtxt);
        phnotext=(TextView)findViewById(R.id.phntext);
        gendertxt=(TextView)findViewById(R.id.sextxt);
        citytxt=(TextView)findViewById(R.id.citytxt);
        dobtxt=(TextView)findViewById(R.id.dobtxt);



        editbtn.setOnClickListener(this);
        editbtn1.setOnClickListener(this);
        //spinner.setOnItemSelectedListener(this);

        ArrayList categories = new ArrayList();
        for(int i = 0; i<arr.length;i++){
            categories.add(arr[i]);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_profile_spinner,categories){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0){
                    return false;
                }
                else{
                    return true;
                }
            }
        };

        adapter.setDropDownViewResource(R.layout.activity_profile_spinner);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText(((profile_myappli)this.getApplication()).username);
        emailtext.setText(((profile_myappli)this.getApplication()).emailtxt);
        gendertxt.setText(((profile_myappli)this.getApplication()).sextext);
        citytxt.setText(((profile_myappli)this.getApplication()).citytext);
        dobtxt.setText(((profile_myappli)this.getApplication()).dobtext);

        Log.d("tag","In onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(((profile_myappli)this.getApplication()).username);
        emailtext.setText(((profile_myappli)this.getApplication()).emailtxt);
        gendertxt.setText(((profile_myappli)this.getApplication()).sextext);
        citytxt.setText(((profile_myappli)this.getApplication()).citytext);
        dobtxt.setText(((profile_myappli)this.getApplication()).dobtext);

        Log.d("tag","In onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag","In onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag","In onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag","In onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.imgbtn:
                Intent intent = new Intent(profile.this,profile_edit.class);
                startActivity(intent);
                break;

            case R.id.imgbtn1:
                profile_dialog dc = new profile_dialog(profile.this);
                dc.pro=this;
                dc.show();

                break;
        }
    }

    public void getDialog(){
        phnotext.setText(((profile_myappli)this.getApplication()).phnno);
    }
}

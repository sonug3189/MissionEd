package com.missionedappdev.missoned;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class profile_edit extends AppCompatActivity implements View.OnClickListener {
    Button editbtn;
    EditText editText,datetxt,emailtxt,citytxt,birthtxt;
    Spinner spinner,genderspin ;
    final Calendar myCalendar = Calendar.getInstance();
    final String arr[] ={"GENDER","MALE","FEMALE" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        editbtn = (Button)findViewById(R.id.edit);
        editText = (EditText)findViewById(R.id.nametxt);
        spinner=(Spinner)findViewById(R.id.spin1);
        emailtxt=(EditText)findViewById(R.id.emailtext);
        citytxt=(EditText)findViewById(R.id.citytext);
        editbtn.setOnClickListener(this);
        editText.setOnClickListener(this);


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



        EditText edittext= (EditText) findViewById(R.id.birth);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {



            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(profile_edit.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datetxt.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onStart() {
        editText.setText(((profile_myappli)this.getApplication()).username);
        emailtxt.setText(((profile_myappli)this.getApplication()).emailtxt);
        citytxt.setText(((profile_myappli)this.getApplication()).citytext);
        birthtxt.setText(((profile_myappli)this.getApplication()).dobtext);
        Log.d("tag","In onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.setText(((profile_myappli)this.getApplication()).username);
        Log.d("tag","In onResume");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.edit:
                String txt = String.valueOf(editText.getText());
                ((profile_myappli)this.getApplication()).username=txt;
                String txt1 = String.valueOf(emailtxt.getText());
                ((profile_myappli)this.getApplication()).emailtxt=txt1;
                String txt2 = String.valueOf(genderspin.getSelectedItem());
                ((profile_myappli)this.getApplication()).sextext=txt2;
                String txt3 = String.valueOf(citytxt.getText());
                ((profile_myappli)this.getApplication()).citytext=txt3;
                String txt4 = String.valueOf(birthtxt.getText());
                ((profile_myappli)this.getApplication()).dobtext=txt4;

                Intent intent = new Intent(profile_edit.this,profile.class);
                startActivity(intent);
        }
    }
}

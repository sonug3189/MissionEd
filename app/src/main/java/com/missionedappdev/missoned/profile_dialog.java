package com.missionedappdev.missoned;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profile_dialog extends Dialog implements View.OnClickListener {


    profile pro;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    EditText changetext;

    public profile_dialog(Activity a) {
        super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dialog);


        changetext=(EditText) findViewById(R.id.chngtxt);
        yes = (Button) findViewById(R.id.btn_save);
        no = (Button) findViewById(R.id.btn_close);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        changetext.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        pro.getDialog();
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String txt = String.valueOf(changetext.getText());
                ((profile_myappli)getContext().getApplicationContext()).phnno=txt;
                //Intent intent = new Intent(dialogClass.this,MainActivity.class);
                pro.getDialog();

            case R.id.btn_close:

                dismiss();
                break;
        }
        dismiss();
    }
}

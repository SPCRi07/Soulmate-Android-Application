package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Feedbacklogout extends AppCompatActivity implements View.OnClickListener {

     SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        session = new SessionManagement(getApplicationContext());
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                alertDialog();
                break;
        }
    }

    private void alertDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Thank You")
                .setIcon(getResources().getDrawable(R.drawable.logo5))
                .setCancelable(false)
                .setPositiveButton("HOME", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Thank You For Using Our App",Toast.LENGTH_LONG).show();
                        session.logoutUser();
                        finish();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}

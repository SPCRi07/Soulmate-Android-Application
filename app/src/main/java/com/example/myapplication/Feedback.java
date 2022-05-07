package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

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
        dialog.setTitle("Thank you")
                .setCancelable(false)
                .setPositiveButton("HOME", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Your Feedback received successfully.",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Feedback.this,BottomNavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}

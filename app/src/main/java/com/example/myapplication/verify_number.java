package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class verify_number extends AppCompatActivity {
    Button verify;
    String num;
    EditText e2;
    TextView skip;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_number);
        verify=(Button)findViewById(R.id.verthisnum);
        e2=(EditText)findViewById(R.id.num);
        skip=(TextView)findViewById(R.id.vernumskip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(verify_number.this, About_me.class);
                startActivity(i);
             }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=e2.getText().toString();
                if(e2.getText().length()==10) {
                    Intent i = new Intent(verify_number.this, verify_otp.class);
                    i.putExtra("ContactNumber", num);
                    startActivity(i);
                }

                else if(e2.getText().length()!=10){
                    Toast.makeText(getApplicationContext(),"enter details",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
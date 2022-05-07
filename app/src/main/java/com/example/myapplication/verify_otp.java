package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verify_otp extends AppCompatActivity {

    Button verifyotp;
    EditText e1,e2,e3,e4;
    String a,b,c,d;
    TextView ContactNumber,send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifi_otp);
        send=findViewById(R.id.txt2);
        verifyotp=(Button)findViewById(R.id.btnverify);
        e1=(EditText)findViewById(R.id.ed1);
        e2=(EditText)findViewById(R.id.ed2);
        e3=(EditText)findViewById(R.id.ed3);
        e4=(EditText)findViewById(R.id.ed4);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("ContactNumber");
        String message2 = "+91" + message;
        ContactNumber = (TextView) findViewById(R.id.txt1);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e1.getText().toString().length()==1){
                    e2.requestFocus();
                    a=e1.getText().toString();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(e1.getText().toString().length()<0) {
                }
            }
        });

        //For OTP Edit Text 2

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e2.getText().toString().length()==1){
                    e3.requestFocus();
                    b=e2.getText().toString();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(e2.getText().toString().length()==0) {
                    e1.requestFocus();
                }
            }
        });

        //For OTP Edit Text 3

        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e3.getText().toString().length()==1){
                    e4.requestFocus();
                    c=e3.getText().toString();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(e3.getText().toString().length()==0) {
                    e2.requestFocus();
                }
            }
        });

        //For OTP Edit Text 4

        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e4.getText().toString().length()==0){
                    e3.requestFocus();
                }
                if(e4.getText().toString().length()==1) {
                    d = e4.getText().toString();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });






        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(verify_otp.this,verify_number.class);
                startActivity(i);
            }
        });

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String total=a+b+c+d;
                   if(e1.getText().length()!=0 && e2.getText().length()!=0 && e3.getText().length()!=0 && e4.getText().length()!=0 ){
                Intent i=new Intent(verify_otp.this,vernumverified.class);
                startActivity(i);
                    }
                    else if(e1.getText().length()==0 && e2.getText().length()==0 && e3.getText().length()==0 && e4.getText().length()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Enter OTP",Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}

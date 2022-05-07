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

public class verify_otp_forgotpass extends AppCompatActivity {

    Button verifyotp;
    EditText e1,e2,e3,e4;
    String a,b,c,d;
    TextView ContactNumber,send;
     String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifi_otp_forgotpass);
        send=findViewById(R.id.txt232);
        verifyotp=(Button)findViewById(R.id.btnverify2);
        e1=(EditText)findViewById(R.id.ed11);
        e2=(EditText)findViewById(R.id.ed12);
        e3=(EditText)findViewById(R.id.ed13);
        e4=(EditText)findViewById(R.id.ed14);
        final Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("forggotemail");
       otp=bundle.getString("emailidotp");
        ContactNumber = (TextView) findViewById(R.id.txt122);
         ContactNumber.setText(message);
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
                if(e4.getText().toString().length()==0) {
                    e3.requestFocus();
                }
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(verify_otp_forgotpass.this,verify_number.class);
                startActivity(i);
            }
        });

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String total=a+b+c+d;
                otp=bundle.getString("emailidotp");
                Integer i=Integer.parseInt(total);
                Integer i2=Integer.parseInt(otp);
                    if(e1.getText().length()==0 || e2.getText().length()==0 || e3.getText().length()==0 || e4.getText().length()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Enter OTP Completely",Toast.LENGTH_SHORT).show();
                    }
                if(e1.getText().length()!=0 && e2.getText().length()!=0 && e3.getText().length()!=0 && e4.getText().length()!=0 ){

                    if(i.equals(i2)){
                        Intent i3=new Intent(verify_otp_forgotpass.this,changeforgotpassword.class);
                        i3.putExtra("emailid1",message);
                        startActivity(i3);
                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Enter Correct OTP",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}

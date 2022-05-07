package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    TextView loginbtn;
    Button submit;
    String text,text2,text3,text4;
    EditText name,name2,name3,name4;
    CharSequence emailchar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);



        submit=(Button)findViewById(R.id.btn_next2);
        name=(EditText)findViewById(R.id.name);
        name2=(EditText)findViewById(R.id.name2);
        name3=(EditText)findViewById(R.id.name3);
        name4=(EditText)findViewById(R.id.name4);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text= name.getText().toString();
                text2= name2.getText().toString();
                text3= name3.getText().toString();
                text4= name4.getText().toString();
                emailchar=name3.getText().toString();
                if(TextUtils.isEmpty(text))  {
                    name.setError("Enter First Name");
                }
                    if(TextUtils.isEmpty(text2))
                    {
                        name2.setError("Enter Last Name");
                 }
                   if(text3.matches("")){
                    name3.setError("Enter EmailID");
                 }
                   if( Patterns.EMAIL_ADDRESS.matcher(text3).matches()) {
                       name3.setError("Enter Valid EmailID");
                   }

                 if(text4.matches("")){
                    name4.setError("Enter Password");

                 }


                  if(!TextUtils.isEmpty(text) && !TextUtils.isEmpty(text2) && !text3.matches("") && !text4.matches("")) {
                    Toast.makeText(getApplicationContext(),"Welcome "+text+" to Soulmate",Toast.LENGTH_LONG).show();
                     Intent i=new Intent(signup.this,login.class);
                     startActivity(i);
                 }

                }

        });




    }
}

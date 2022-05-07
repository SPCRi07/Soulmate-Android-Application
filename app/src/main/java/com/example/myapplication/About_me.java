package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class About_me extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button complete;
    String[] textArray;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        textView=(TextView)findViewById(R.id.counter);
        editText=(EditText) findViewById(R.id.edaboutmedesc);
        complete=findViewById(R.id.btncomplete);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                textView.setTextColor(getResources().getColor(R.color.colorRed));

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                text = editText.getText().toString();
                text = text.replace("\n"," ");
               textArray=text.split(" ");
                textView.setText("Words: " + textArray.length + " /25" );
            }

            @Override
            public void afterTextChanged(Editable s) {

                textView.setTextColor(getResources().getColor(R.color.colorGreen));
            }

        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView.getText().toString().matches("") && editText.getText().length()!=0){
                    uservalues.aboutme=editText.getText().toString();
                Intent i=new Intent(About_me.this,activity_username.class);
                startActivity(i);
            }
                else{
                    Toast.makeText(getApplicationContext(),"Enter About You",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void checktext(String text) {
        text = editText.getText().toString();
        text = text.replace("\n"," ");
        textArray=text.split(" ");
        textView.setText("Words: " + textArray.length + " /25" );
    }



}

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class vernumverified extends AppCompatActivity {


    Button ok;
    TextView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_success);
    ok=(Button)findViewById(R.id.btnok);
        v1=(TextView)findViewById(R.id.phonedesc2);

        ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i=new Intent(vernumverified.this,About_me.class);
            startActivity(i);
        }
    });
    }
}

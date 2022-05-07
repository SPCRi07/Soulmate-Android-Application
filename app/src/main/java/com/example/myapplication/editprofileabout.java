package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class editprofileabout extends AppCompatActivity {

    TextView txteditcareer,txteditfamily;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofileabout);

        txteditcareer=(TextView)findViewById(R.id.editcareer);

        txteditfamily=(TextView)findViewById(R.id.editfamily);

        txteditcareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(editprofileabout.this,careerdetails.class);
                startActivity(i);
            }
        });

        txteditfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(editprofileabout.this,FamildDetails.class);
                startActivity(i);
            }
        });


    }
}

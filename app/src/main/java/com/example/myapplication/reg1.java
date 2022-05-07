package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class reg1 extends AppCompatActivity {

    CardView c1, c2, c3, c4, c5, c6;
    TextView loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forwho2_reg);
        c1 = (CardView) findViewById(R.id.c1);
        c2 = (CardView) findViewById(R.id.c2);
        c3 = (CardView) findViewById(R.id.c3);
        c4 = (CardView) findViewById(R.id.c4);
        c5 = (CardView) findViewById(R.id.c5);
        c6 = (CardView) findViewById(R.id.c6);


        loginbtn=(TextView) findViewById(R.id.btnlogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(reg1.this,login.class);
                startActivity(i);
            }
        });
                    c1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c1.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            uservalues.forwho="myself";
                            startActivity(i);

                            }
                    });
                    c2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c2.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            startActivity(i);
                            uservalues.forwho="myself";
                            startActivity(i);
                        }
                    });
                    c3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c3.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            uservalues.forwho="son";
                            startActivity(i);
                        }
                    });
                    c4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c4.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            uservalues.forwho="sister";
                            startActivity(i);
                        }
                    });
                    c5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c5.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            uservalues.forwho="brother";
                            startActivity(i);
                        }
                    });
                    c6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            c6.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Intent i = new Intent(reg1.this, personaldetails.class);
                            uservalues.forwho="relative";
                            startActivity(i);
                        }
                    });

    }
}




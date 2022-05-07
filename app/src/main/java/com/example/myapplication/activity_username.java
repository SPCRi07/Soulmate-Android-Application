package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class activity_username extends AppCompatActivity {


        EditText a1;
        Button next;
        String s1;
        ImageView a2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        a1=(EditText)findViewById(R.id.textusername);
        next=(Button)findViewById(R.id.usernamenextbtn);
        a2=(ImageView)findViewById(R.id.backarrow);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s1=a1.getText().toString();
                if(s1.length()!=0){
                    uservalues.username=s1;
                    Intent i=new Intent(activity_username.this,FamildDetails.class);
                    startActivity(i);
                }
                else
                {
                    LoadingDialog dialog=new LoadingDialog(activity_username.this);
                    dialog.startLoadingDialog("You Have to Enter Your UserName","OK");
                }
                     }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_username.this,About_me.class);
                startActivity(i);
            }
        });
    }
}

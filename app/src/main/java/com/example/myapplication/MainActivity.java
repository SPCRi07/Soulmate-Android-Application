package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {

    TextView a1,a2;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);
        // Call the function callInstamojo to start payment here
        a1=(TextView)findViewById(R.id.textsoulmate);
        a2=(TextView)findViewById(R.id.textsoulmate2);
        Animation anim2= AnimationUtils.loadAnimation(this,R.anim.slide_up);
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.slide_down);
        a1.startAnimation(anim);
        a2.startAnimation(anim2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(MainActivity.this,welcome_activity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
      }

}

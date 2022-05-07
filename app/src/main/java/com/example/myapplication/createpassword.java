package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityCreatepasswordBinding;

public class createpassword extends AppCompatActivity {


    ActivityCreatepasswordBinding binding;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SessionManagement sessionManagement=new SessionManagement(this);
        binding=ActivityCreatepasswordBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle("Create Password For Authentication");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        binding.changeapppassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.keycreatepassword.getText().toString().length()!=6){
                    binding.keycreatepassword.setError("Enter 6 Digit Password");
                }
                else
                {
                sessionManagement.createpassword(binding.keycreatepassword.getText().toString());
                startActivity(new Intent(createpassword.this,BottomNavigationActivity.class));
                }
            }
        });
    }
}

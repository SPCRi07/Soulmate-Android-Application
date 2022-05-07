package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.myapplication.databinding.ActivitySecuritypasswordBinding;

public class securitypassword extends AppCompatActivity {

    ActivitySecuritypasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySecuritypasswordBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle("Password Protection");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final SessionManagement sessionManagement=new SessionManagement(getApplicationContext());

        if(sessionManagement.haspassword()){

            binding.changepassword.setVisibility(View.VISIBLE);
            binding.removepassword.setVisibility(View.VISIBLE);
            binding.passwordSwitch.setChecked(true);
        }

        binding.passwordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    startActivity(new Intent(securitypassword.this,createpassword.class));
                }
                else{
                    sessionManagement.RemovePassword();
                }
            }
        });

        binding.changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(securitypassword.this,ChangeApppassowrd.class));
            }
        });

        binding.removepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagement.RemovePassword();
                binding.changepassword.setVisibility(View.INVISIBLE);
                binding.passwordSwitch.setChecked(false);
            }
        });
    }
}

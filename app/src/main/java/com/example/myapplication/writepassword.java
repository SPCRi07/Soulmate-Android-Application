package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.example.myapplication.databinding.ActivityWritepasswordBinding;

import java.util.HashMap;
import java.util.Random;

public class writepassword extends AppCompatActivity {

    ActivityWritepasswordBinding binding;
    SessionManagement sessionManagement;
    AlertDialog.Builder builder;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWritepasswordBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
         sessionManagement=new SessionManagement(this);
        HashMap<String, String> user=sessionManagement.getUserDetails();
        builder=new AlertDialog.Builder(this);
        final String oldpass=user.get(SessionManagement.UserPassword);
        final String email=user.get(SessionManagement.KEY_EMAIL);
        final String gender=user.get(SessionManagement.USER_GENDER);

        if(gender.equals("Female")){
            binding.welcomegif.setImageResource(R.drawable.girlgif);
        }
        else
        {
            binding.welcomegif.setImageResource(R.drawable.boygif);
        }

        binding.loginapppassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t=binding.enteredpassword;
                String t1=binding.enteredpassword.getText().toString();
                if(t.getText().length()!=6){
                    t.setError("Enter Correct Password");
                }
                else if( t1.equals(oldpass)){
                    if(sessionManagement.isnewpass()){
                        binding.welcomegif.setImageResource(R.drawable.lockgif);
                        startActivity(new Intent(writepassword.this, ChangeApppassowrd.class));
                    }
                    else {
                        binding.welcomegif.setImageResource(R.drawable.lockgif);
                        startActivity(new Intent(writepassword.this, BottomNavigationActivity.class));
                    }}
            }

        });
        binding.forgotkeypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage(email)
                        .setCancelable(true)
                        .setIcon(R.drawable.password)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sendemail(email);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Sending Password to");
                if(gender.matches("Male")){
                    alert.setIcon(R.drawable.genmale);
                }
                else{
                    alert.setIcon(R.drawable.genfemale);
                }
                alert.show();
            }
        });
    }
    public void sendemail(final String email)
    {
        final String id;
        Random rand = new Random();
        id = String.format("%04d", rand.nextInt(1000000));
        BackgroundMail.newBuilder(writepassword.this)
                .withUsername("soulmateofficialapp@gmail.com")
                .withPassword("solanki@115")
                .withMailto(email)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Recover Your Password Of SOULMATE")
                .withBody("Your Password is: "+id)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        sessionManagement.changepassword(id);
                        sessionManagement.changepassnewtime();
                        Toast.makeText(getApplicationContext(),"New Password send to "+email+"",Toast.LENGTH_LONG).show();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(),"Email can't send to "+email+"",Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }
}

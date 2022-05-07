package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.example.myapplication.databinding.ActivityChangeApppassowrdBinding;

import java.util.HashMap;
import java.util.Random;

public class ChangeApppassowrd extends AppCompatActivity {
    ActivityChangeApppassowrdBinding binding;
    SessionManagement sessionManagement;
    AlertDialog.Builder builder;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangeApppassowrdBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        builder=new AlertDialog.Builder(this);
         sessionManagement=new SessionManagement(this);
        final HashMap<String, String> user=sessionManagement.getUserDetails();
        getSupportActionBar().setTitle("Change your Default Password For App");
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
           final String oldpass=user.get(SessionManagement.UserPassword);
        final String email=user.get(SessionManagement.KEY_EMAIL);
        final String gender=user.get(SessionManagement.USER_GENDER);
                if(sessionManagement.isnewpass()){
                    sessionManagement.changepassnewtimeremove();
                }

            binding.changeapppassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a1=binding.keyoldpassword.getText().length();
                    int a2=binding.keynewpassword.getText().length();
                    int a3=binding.keyconfirmnewpass.getText().length();
                    String old=binding.keyoldpassword.getText().toString();
                    if (a1!=6){
                        binding.keyoldpassword.setError("Enter Password Completely");
                    }
                    else if (a2!=6){
                        binding.keynewpassword.setError("Enter Password Completely");
                    }
                    else if (a3!=6){
                        binding.keyconfirmnewpass.setError("Enter Password Completely");
                    }

                    else{
                                String a=binding.keyconfirmnewpass.getText().toString();
                                String b=binding.keynewpassword.getText().toString();

                                if(!a.equals(b)){
                            binding.keyconfirmnewpass.setError("Confirmation Password Is not Matching");
                        }

                        else if(oldpass.equals(old)){
                            sessionManagement.changepassword(a);
                         startActivity(new Intent(ChangeApppassowrd.this,BottomNavigationActivity.class));
                        }

                        else{
                            binding.keyoldpassword.setError("Old Password is not Matching");
                            }
                        }
                }
            });
                binding.changekeypassword.setOnClickListener(new View.OnClickListener() {
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
        BackgroundMail.newBuilder(ChangeApppassowrd.this)
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

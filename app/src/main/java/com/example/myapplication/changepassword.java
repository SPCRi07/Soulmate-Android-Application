package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;
import mx.jesusmartinoza.widget.SweetPassword;

public class changepassword extends AppCompatActivity {

    SweetPassword  sweetpasswordold,swnew,swconfirm;
    Button btnchangepassword;
    String oldpassword,newpassword,confirmpassword;
    SessionManagement sessionManagement;
    String id;
  @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        sweetpasswordold=(SweetPassword)findViewById(R.id.sweetpasswordexisting);
        sweetpasswordold.setInterpolator(new BounceInterpolator());
        sweetpasswordold.setAnimDuration(400);
        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);

        swnew=(SweetPassword)findViewById(R.id.sweetpasswordcurrent);
        swconfirm=(SweetPassword)findViewById(R.id.confirm_password);
        swnew.setInterpolator(new BounceInterpolator());
        swnew.setAnimDuration(400);
        swconfirm.setInterpolator(new BounceInterpolator());
        swconfirm.setAnimDuration(400);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        btnchangepassword=(Button)findViewById(R.id.btnchangepassword);

        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                oldpassword=sweetpasswordold.getPassword();
                newpassword=swnew.getPassword();
                confirmpassword=swconfirm.getPassword();
                if(oldpassword.matches(""))
                {
                    sweetpasswordold.setFocusable(true);
                    sweetpasswordold.setAutofillHints("Enter Correct Old Password");
                    sweetpasswordold.setRevealOnFocusHint(true);
                }
                if(newpassword.matches("")){

                    swnew.setFocusable(true);
                    swnew.setAutofillHints("Enter Correct Password");
                    swnew.setRevealOnFocusHint(true);
                }
                if(confirmpassword.matches("")){

                    swconfirm.setFocusable(true);
                    swconfirm.setAutofillHints("Enter Correct Password");
                    swconfirm.setRevealOnFocusHint(true);
                }

                if(!validatePassword(newpassword) || !validatePassword(confirmpassword))
                {
                 Toast.makeText(getApplicationContext(),"Please Enter Strong Password",Toast.LENGTH_SHORT).show();
                }
                if(!oldpassword.matches("") && !newpassword.matches("") && !confirmpassword.matches("") && newpassword.matches(confirmpassword) && validatePassword(newpassword) && validatePassword(confirmpassword))
                {
                   match();
                }
            }
        });

    }
    public boolean validatePassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    private void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",id);
        rp.put("oldpass",oldpassword);
        rp.put("newpass",newpassword);

        client.post(Constants.Change_password,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.getString("res").equals("ok")) {
                        Intent i = new Intent(changepassword.this, editprofile.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Password Changed",Toast.LENGTH_SHORT).show();
                    }
                    else if(response.getString("res").equals("incorrect"))
                    {
                        Toast.makeText(getApplicationContext(),"Enter Correct Current Password ",Toast.LENGTH_SHORT).show();
                        sweetpasswordold.setBackgroundColor(Color.RED);
                       }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

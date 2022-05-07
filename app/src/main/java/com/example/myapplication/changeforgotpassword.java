package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class changeforgotpassword extends AppCompatActivity {

    SweetPassword swconfirm;
    Button btnchangepassword;
    String confirmpassword;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword_email);
        swconfirm=(SweetPassword)findViewById(R.id.change_password_email);
         swconfirm.setInterpolator(new BounceInterpolator());
        swconfirm.setAnimDuration(400);
         bundle = getIntent().getExtras();
        final String message = bundle.getString("forggotemail");
        btnchangepassword=(Button)findViewById(R.id.btnchangepassword_email);
        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                 confirmpassword=swconfirm.getPassword();

                if(confirmpassword.matches("")){

                    swconfirm.setFocusable(true);
                    swconfirm.setAutofillHints("Enter Correct Password");
                    swconfirm.setRevealOnFocusHint(true);
                }

                if(!validatePassword(confirmpassword))
                {
                 Toast.makeText(getApplicationContext(),"Please Enter Strong Password",Toast.LENGTH_SHORT).show();
                }
                if(!confirmpassword.matches("") && validatePassword(confirmpassword))
                {
                    final String message = bundle.getString("emailid1");
                   match(message,confirmpassword);
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
    private void match(String id,String password){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("emailid",id);
        rp.put("password",password);

        client.post(Constants.CHANGE_FORGOT_EMAILPASSWORD,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.getString("res").equals("ok")) {
                        Intent i = new Intent(changeforgotpassword.this, login.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Password Changed",Toast.LENGTH_SHORT).show();
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

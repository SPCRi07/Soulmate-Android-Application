package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class forgotpass extends AppCompatActivity {



    AsyncHttpClient client;
    EditText email;
    RequestParams rp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        Button verify=(Button)findViewById(R.id.btn_next);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=(EditText)findViewById(R.id.forgotpass);
                String text=email.getText().toString();
                if(TextUtils.isEmpty(text) ) {
                  email.setError("Enter Valid EmailID");
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(text).matches())
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email Address",Toast.LENGTH_LONG).show();
                }
                 if(!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches())
                {
                    match(text);
                }
            }
        });

    }

    private void match(String city){
        client = new AsyncHttpClient();
        rp= new RequestParams();
        rp.put("emailid",city);
         client.post(Constants.FETCH_URL,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if(response.getString("res").equals("ok")) {
                        sendemail();
                       }
                    else
                    {
                        Toast.makeText(getApplicationContext(),email.getText().toString()+" is not in Our Database",Toast.LENGTH_LONG).show();
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
                 }
        });
    }

    public void sendemail(){
        final String id;
        Random rand = new Random();
        id = String.format("%04d", rand.nextInt(10000));
        BackgroundMail.newBuilder(this)
                .withUsername("soulmateofficialapp@gmail.com")
                .withPassword("solanki@115")
                .withMailto(email.getText().toString())
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Recover Your Password Of SOULMATE")
                .withBody("Your Otp is: "+id)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                        Toast.makeText(getApplicationContext(),"Confirmation send to "+email.getText().toString()+"",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(forgotpass.this,verify_otp_forgotpass.class);
                        i.putExtra("emailidotp",id);
                        i.putExtra("forggotemail",email.getText().toString());
                        startActivity(i);
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(),"Email can't send to "+email.getText().toString()+"",Toast.LENGTH_LONG).show();
                    }
                })
                .send();
    }
}


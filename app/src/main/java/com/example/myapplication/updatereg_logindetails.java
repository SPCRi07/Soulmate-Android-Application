package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class updatereg_logindetails extends AppCompatActivity {

     EditText ed1,ed2,ed4;
     String email1,Password;
    Button login;
    String email,profileid,id;
    SharedPreferences sharedpreferences;
    SessionManagement sessionManagement;
    public  ProgressDialog progressDialog;
    public String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatereg_logindetails);
        login=(Button)findViewById(R.id.update_logindetails_page);
        ed1=findViewById(R.id.updateedname);
        ed2=findViewById(R.id.updateedemail);
        ed4=findViewById(R.id.updatenum);

        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);
        email1=ed2.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().isEmpty() && ed2.getText().toString().isEmpty()  && ed4.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter details", Toast.LENGTH_SHORT).show();
                }
                if (ed1.getText().length() == 0) {
                    ed1.setError("Enter Name");
                }
                if (ed2.getText().length() == 0) {
                    ed2.setError("Enter Email Address");
                }
                if (ed4.getText().length() == 0) {
                    ed4.setError("Enter Phone Number");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(ed2.getText().toString()).matches()) {
                    ed2.setError("Enter Valid Email address");
                } else if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(ed2.getText().toString()).matches()) {
                    uservalues.fullname = ed1.getText().toString();
                    uservalues.mobilenum = ed4.getText().toString();
                    uservalues.emailid = ed2.getText().toString();
                  signin();
                //  progressDialog= ProgressDialog.show(updatereg_logindetails.this,"Registering","Please Wait",false,false);
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
    public void signin(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();

        rp.put("name", uservalues.fullname);
        rp.put("profileid",id);
         rp.put("emailid", uservalues.emailid);
        rp.put("mobilenum", uservalues.mobilenum);


        client.post(Constants.Update_logindetails, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Toast.makeText(updatereg_logindetails.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(updatereg_logindetails.this,"Update Success",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(updatereg_logindetails.this,editprofile.class);
                            startActivity(i);
                     }
                    else
                    {
                        Toast.makeText(updatereg_logindetails.this,"Update Failed",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(updatereg_logindetails.this,statusCode + responseString,Toast.LENGTH_SHORT).show();

            }
        });

    }

}

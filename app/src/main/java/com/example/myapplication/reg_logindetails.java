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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class reg_logindetails extends AppCompatActivity {

     EditText ed1,ed2,ed3,ed4;
     String email1,Password;
    SessionManagement session;
    Button login;
    String email,profileid;
    SharedPreferences sharedpreferences;
    SessionManagement sessionManagement;
    public  ProgressDialog progressDialog;
    public String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_logindetails);
        login=(Button)findViewById(R.id.nextlogin);
        ed1=findViewById(R.id.edname);
        ed2=findViewById(R.id.edemail);
        ed3=findViewById(R.id.edpassword);
        ed4=findViewById(R.id.num);
        email1=ed2.getText().toString();
        Password=ed3.getText().toString();
        sessionManagement=new SessionManagement(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
                if (ed1.getText().toString().isEmpty() && ed2.getText().toString().isEmpty() && ed3.getText().toString().isEmpty() && ed4.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter details", Toast.LENGTH_SHORT).show();

                }


                if (ed1.getText().length() == 0) {
                    ed1.setError("Enter Name");
                }
                if (ed2.getText().length() == 0) {
                    ed2.setError("Enter Email Address");
                }

                if (ed3.getText().length() == 0) {
                    ed3.setError("Enter Password");
                }
                if (ed4.getText().length() == 0) {
                    ed4.setError("Enter Phone Number");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(ed2.getText().toString()).matches()) {
                    ed2.setError("Enter Valid Email address");
                } else if (!validatePassword(ed3.getText().toString())) {
                    ed3.setError("Enter Strong Password");
                } else if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed3.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(ed2.getText().toString()).matches() && validatePassword(ed3.getText().toString())) {
                    uservalues.fullname = ed1.getText().toString();
                    uservalues.mobilenum = ed4.getText().toString();
                    uservalues.emailid = ed2.getText().toString();
                    uservalues.pass = ed3.getText().toString();
                    signin();
                  progressDialog= ProgressDialog.show(reg_logindetails.this,"Registering","Please Wait",false,false);


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
        rp.put("fullname", uservalues.fullname);
        rp.put("pass", uservalues.pass);
        rp.put("cast", uservalues.cast);
        rp.put("city", uservalues.city);
        rp.put("dob", uservalues.dob);
        rp.put("education", uservalues.education);
        rp.put("emailid", uservalues.emailid);
        rp.put("employeein", uservalues.employeein);
        rp.put("forwho", uservalues.forwho);
        rp.put("gender", uservalues.gender);
        rp.put("state", uservalues.state);
        rp.put("religion", uservalues.religion);
        rp.put("occupation", uservalues.occupation);
        rp.put("mothertongue", uservalues.mothertongue);
        rp.put("mobilenum", uservalues.mobilenum);
        rp.put("income", uservalues.income);
        rp.put("maritialstatus", uservalues.maritialstatus);

        client.post(Constants.signup_Url, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Toast.makeText(com.example.myapplication.reg_logindetails.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();

                            JSONArray ja=response.getJSONArray("data");
                                for(int i=0;i<ja.length();i++){
                                    JSONObject jo=ja.getJSONObject(i);
                                   id1=jo.optString("profileid");
                                }
                            SharedPreferences.Editor editor=sharedpreferences.edit();
                            editor.putString("email",uservalues.emailid);
                            editor.putString("id",id1);
                            editor.apply();
                            progressDialog.dismiss();
                            Intent i=new Intent(reg_logindetails.this,appearance.class);
                            startActivity(i);
                    }
                    if(response.getString("res").equals("failed")){
                        Toast.makeText(com.example.myapplication.reg_logindetails.this,"this Email is Already Registered",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    else
                    {
                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(com.example.myapplication.reg_logindetails.this,statusCode + responseString,Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }


        });

    }

}

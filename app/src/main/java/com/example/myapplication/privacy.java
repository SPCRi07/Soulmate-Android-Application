package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class privacy extends AppCompatActivity {

    SessionManagement sessionManagement;
   public  SwitchCompat envSwitch,premimuswitch,colorswitch,likeswitch;
   ImageView lock;
    HashMap<String, String> user;
    String profile;
    String check1,gender;
    AlertDialog.Builder builder;

    @SuppressLint("RestrictedApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        sessionManagement=new SessionManagement(getApplicationContext());
        user=sessionManagement.getUserDetails();
        builder=new AlertDialog.Builder(this);
        profile=user.get(SessionManagement.KEY_ID);
        gender=user.get(SessionManagement.Gender);
        envSwitch = findViewById(R.id.photo_switch);
        premimuswitch=findViewById(R.id.premium_switch);
        colorswitch=findViewById(R.id.theme_switch);
        likeswitch=findViewById(R.id.like_switch);
        getSupportActionBar().setTitle("Privacy Setting");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        lock=findViewById(R.id.photoprivacy);
        Boolean abc= sessionManagement.isPremium();
        if (abc)
        { select(profile);
        envSwitch.setVisibility(View.VISIBLE);
        premimuswitch.setVisibility(View.VISIBLE);
        colorswitch.setVisibility(View.VISIBLE);
        likeswitch.setVisibility(View.VISIBLE);
        }
        else
        {
            lock.setVisibility(View.VISIBLE);
        }
        if(sessionManagement.isdark()){
            colorswitch.setChecked(true);
        }

        envSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        add(profile);
                    }
                    else
                    {
                    delete(profile);
                    }
            }
        });
        premimuswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    builder.setMessage("It will Cancel your Membership")
                            .setCancelable(true)
                            .setIcon(R.drawable.ic_upgrade)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    profiledelete(profile);
                                    finish();
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
                    alert.setTitle("Are you Sure?");
                    if(gender.matches("Male")){
                        alert.setIcon(R.drawable.genmale);
                    }
                    else{
                        alert.setIcon(R.drawable.genfemale);
                    }
                    alert.show();
                }
            }
        });

        colorswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sessionManagement.SETDARKMODE();
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sessionManagement.REMOVEDARKMODE();
                }
            }

        });

        likeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                sessionManagement.SETLIKEMODE();}
                    else
                    {
                        sessionManagement.REMOVELIKEMODE();
                    }
            }
        });

        if(sessionManagement.isLike()){
            likeswitch.setChecked(true);
        }

    }

    private void add(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",profileid);

        client.post(Constants.Add_to_photolock,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {

                    } else {
                        Toast.makeText(getApplicationContext(), "FAILED TO success", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    private void delete(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",profileid);


        client.post(Constants.Delete_from_photolock,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {

                    }
                    else
                        {
                        Toast.makeText(getApplicationContext(), "FAILED TO Delete", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void select(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",profileid);

        client.post(Constants.FETCH_URL_PROFILEID,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {

                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            check1=jo.optString("photolock");
                            if(check1.matches("true")){
                                envSwitch.setChecked(true);
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "FAILED TO RETRIEVE", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
        }
        });
    }
    private void profiledelete(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",profileid);

        client.post(Constants.Delete_subscription,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {
                        sessionManagement.removePremium();
                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

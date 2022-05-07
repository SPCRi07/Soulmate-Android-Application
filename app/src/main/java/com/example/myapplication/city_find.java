package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivitySelectCityBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class city_find extends AppCompatActivity {

    String[] ids,ids1;
    SessionManagement session;
    String id;
    ProgressBar bar;
    ActivitySelectCityBinding binding;
    ArrayAdapter adapter;
    ArrayList list,list2;
    @Override
    protected void onStart() {
        match();
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectCityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("First Date ");
        session=new SessionManagement(getApplicationContext());
        list=new ArrayList();
        list2=new ArrayList();
        adapter=new ArrayAdapter(city_find.this,android.R.layout.simple_expandable_list_item_1,list);
        String s=session.getUserDetails().get(SessionManagement.Name);
        binding.textforcitychoosing.setText("Hey, "+s+"  Choose Your Partner");
        binding.buttonforcities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int po=binding.searchmatchspinner.getSelectedItemPosition();
                String a=list2.get(po).toString();
                fetch2(a);
            }
        });
    }

    private void match() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        final HashMap<String, String> user = session.getUserDetails();
        String gen = user.get(SessionManagement.KEY_ID);
        rp.put("fromprofileid", gen);

        client.post(Constants.MATCHES_PROFILEID, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    id=user.get(SessionManagement.Profileid);
                    if (response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        ids = new String[ja.length()];
                        ids1 = new String[ja.length()];
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            ids[i] = jo.getString("toprofile");
                            ids1[i] = jo.getString("fromprofile");
                            if (ids1[i].matches(id)) {
                                fetch(ids[i]);
                            }
                            if (ids[i].matches(id)) {
                                fetch(ids1[i]);
                            }
                        }
                    } else {
                        binding.cityprogressbar.setVisibility(View.INVISIBLE);
                        binding.citynone.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }


    private void fetch(String profileid) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid", profileid);
        client.post(Constants.FETCH_URL_PROFILEID, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if (response.getString("res").equals("ok")) {
                        String Name;
                        String imageurl;
                        String age;
                        String profileid;
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            profileid = jo.optString("profileid");
                            Name = jo.getString("name");
                            imageurl = jo.optString("image");
                            age = jo.optString("city");
                            list.add(Name);
                            list2.add(profileid);
                          }
                        binding.searchmatchspinner.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                binding.cityprogressbar.setVisibility(View.INVISIBLE);
                binding.couplehands.setVisibility(View.VISIBLE);
                binding.buttonforcities.setVisibility(View.VISIBLE);
                binding.searchmatchspinner.setVisibility(View.VISIBLE);
                    binding.textforcitychoosing.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
    private void fetch2(String profileid) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid", profileid);
        client.post(Constants.FETCH_URL_PROFILEID, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Intent i2 = new Intent(city_find.this, choosecity.class);
                    if (response.getString("res").equals("ok")) {
                        String profileid;
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            profileid = jo.optString("profileid");
                            i2.putExtra("Name", jo.getString("name"));
                            i2.putExtra("City", jo.getString("city"));
                            i2.putExtra("Image", jo.getString("image"));
                            i2.putExtra("Profileid", profileid);
                        }
                        startActivity(i2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
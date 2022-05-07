package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.ActivityAboutBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;


//Our class extending fragment
public class Tab1profile extends Fragment {

    private TextView n1,city,id1,mothertongue1,cast,religion,employee,education,occupation,state,maritialstatus;
    private TextView aboutme,aboutcareereducation,aboutincome,username1,aboutoccupation,aboutcareerempin;
    TextView height,income;
    private SessionManagement sessionManagement;
    String name2,username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.activity_about, container, false);
        sessionManagement=new SessionManagement(getContext());
        sessionManagement.getUserDetails();
        HashMap<String, String> user = new HashMap<String, String>();
       n1=(TextView)rootview.findViewById(R.id.aboutname);
        id1=(TextView)rootview.findViewById(R.id.aboutid);
        city=(TextView)rootview.findViewById(R.id.obtainedcity);
        cast=(TextView)rootview.findViewById(R.id.aboutcaste);
        religion=(TextView)rootview.findViewById(R.id.aboutreligion2);
        income=(TextView)rootview.findViewById(R.id.aboutincome);
        height=(TextView)rootview.findViewById(R.id.aboutheight);
        employee=(TextView)rootview.findViewById(R.id.aboutemployedin2);
        education=(TextView)rootview.findViewById(R.id.abouteducation);
        occupation=(TextView)rootview.findViewById(R.id.aboutoccupation);
         state=(TextView)rootview.findViewById(R.id.aboutstate);
        maritialstatus=(TextView)rootview.findViewById(R.id.aboutemarritialstatus);
        aboutme=(TextView)rootview.findViewById(R.id.aboutdescofaboutme);
        aboutcareereducation=(TextView)rootview.findViewById(R.id.aboutcareereducation);
        aboutincome=(TextView)rootview.findViewById(R.id.aboutcareerincome);
        username1=(TextView)rootview.findViewById(R.id.aboutyear);
        aboutoccupation=(TextView)rootview.findViewById(R.id.aboutcareeroccupation);
        mothertongue1=(TextView)rootview.findViewById(R.id.aboutmothertongue);
        aboutcareerempin=(TextView)rootview.findViewById(R.id.aboutcareeremploiedin);
        Bundle budle= Objects.requireNonNull(getActivity()).getIntent().getExtras();
        assert budle != null;
        name2=budle.getString("name1");
         fetch(name2);
        return  rootview;
    }

    private void fetch(String name){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",name);

        client.post(Constants.FETCH_URL_PROFILEID,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            n1.setText(jo.getString("name"));
                            city.setText(jo.optString("city"));
                            id1.setText("ID("+jo.optString("profileid")+")");
                            mothertongue1.setText(jo.optString("mothertongue"));
                            cast.setText(jo.optString("cast"));
                            height.setText(jo.optString("height"));
                            religion.setText(jo.optString("religion"));
                            employee.setText(jo.optString("employeein"));
                            income.setText(jo.optString("income"));
                            education.setText(jo.optString("education"));
                            occupation.setText(jo.optString("occupation"));
                            state.setText(jo.optString("state"));
                            maritialstatus.setText(jo.optString("maritialstatus"));
                            aboutme.setText("About Me:- "+jo.optString("aboutme"));
                            aboutcareereducation.setText(jo.optString("education"));
                            aboutincome.setText(jo.optString("income"));
                            username1.setText(jo.optString("username"));
                            aboutoccupation.setText(jo.optString("occupation"));
                            uservalues.mobilenum=jo.getString("mobilenum");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    
}
}
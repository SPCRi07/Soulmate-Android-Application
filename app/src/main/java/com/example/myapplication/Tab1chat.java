package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


//Our class extending fragment
public class Tab1chat extends Fragment {
    ArrayList<requestcarditems> list=new ArrayList<>();
    String[] ids;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Activity context;
    SessionManagement session;
    TextView none;
    ProgressBar bar;

    @Override
    public void onStart() {
        super.onStart();
        match();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.tab1chat, container, false);
        context= (Activity) getContext();
        session=new SessionManagement(rootview.getContext());
        adapter=new requestlistcardadapter(list);
        none=(TextView)rootview.findViewById(R.id.requestnone);
        bar=(ProgressBar) rootview.findViewById(R.id.requestprogress);
        bar.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.requestlistview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        return rootview;
    }

    public void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        HashMap<String, String> user = session.getUserDetails();
        String gen=user.get(SessionManagement.KEY_ID);
        rp.put("fromprofileid",gen);
        client.post(Constants.REQUEST_PROFILEID,rp,new JsonHttpResponseHandler(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if(response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        ids=new String[ja.length()];
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            ids[i]=jo.getString("fromprofile");
                            fetch(ids[i]);
                        }
                    }
                    else
                    {
                        none.setVisibility(View.VISIBLE);
                        bar.setVisibility(View.INVISIBLE);
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



    private void fetch(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
         rp.put("profileid",profileid);
        client.post(Constants.REQUEST_PROFILEID_DATA,rp,new JsonHttpResponseHandler(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if(response.getString("res").equals("ok")) {
                        String Name;
                        String imageurl;
                        String age;
                        String profileid,email;
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            profileid=jo.getString("profileid");
                            Name =jo.getString("name");
                            imageurl=jo.optString("image");
                            age=jo.optString("age");
                            email=jo.optString("emailid");
                            list.add( new requestcarditems(Name,imageurl,profileid,age,email));
                            bar.setVisibility(View.INVISIBLE);
                        }
                    }
                    else
                    {
                        none.setVisibility(View.VISIBLE);
                        bar.setVisibility(View.INVISIBLE);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }

}

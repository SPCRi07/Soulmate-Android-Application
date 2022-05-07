package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class nav_match_fragment extends Fragment {
    TextView none;
    ArrayList<matchescarditems> list=new ArrayList<>();
    String[] ids,ids1;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Activity context;
    SessionManagement session;
    String id;
    ProgressBar bar;
    HashMap<String,String> user;

    @Override
    public void onStart() {
        match();
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.nav_matches,container,false);
        context= (Activity) getContext();
        none=(TextView)rootview.findViewById(R.id.matchesnone);
        bar=(ProgressBar)rootview.findViewById(R.id.matchprogress);
        session=new SessionManagement(rootview.getContext());
        adapter=new matchescardadapter(list);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.matcheslistview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        user=session.getUserDetails();
        id=user.get(SessionManagement.Profileid);

        return rootview;
    }

    private void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        HashMap<String, String> user = session.getUserDetails();
        String gen=user.get(SessionManagement.KEY_ID);
        rp.put("fromprofileid",gen);

        client.post(Constants.MATCHES_PROFILEID,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {

                    if(response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        ids=new String[ja.length()];
                        ids1=new String[ja.length()];
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            ids[i]=jo.getString("toprofile");
                            ids1[i]=jo.getString("fromprofile");
                             if(ids1[i].matches(id)){
                                fetch(ids[i]);
                            }
                             if(ids[i].matches(id)){
                                 fetch(ids1[i]);
                             }
                            }
                    }
                    else
                    {
                        bar.setVisibility(View.INVISIBLE);
                        none.setVisibility(View.VISIBLE);
                     }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }



    private void fetch(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",profileid);
        client.post(Constants.FETCH_URL_PROFILEID,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if(response.getString("res").equals("ok")) {
                        String Name;
                        String imageurl;
                        String age;
                        String profileid;
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            profileid=jo.optString("profileid");
                            Name =jo.getString("name");
                            imageurl=jo.optString("image");
                            age=jo.optString("age");
                            list.add( new matchescarditems(Name,imageurl,profileid,age));
                        }
                    }
                    else
                    {
                     }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                bar.setVisibility(View.INVISIBLE);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

            }
        });
    }
}

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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


public class Tab3chat extends Fragment {

    ImageView none;
    TextView nonetext;
    ArrayList<photorequestcarditems> list=new ArrayList<>();
    String[] ids;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Activity context;
    SessionManagement session;
    ProgressBar bar;

    @Override
    public void onStart() {
        super.onStart();
    match();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootview= inflater.inflate(R.layout.tab3chat, container, false);

       none=(ImageView)rootview.findViewById(R.id.photorequestnonephoto);
       nonetext=(TextView) rootview.findViewById(R.id.photorequesttextView);
        context= (Activity) getContext();
        bar=(ProgressBar)rootview.findViewById(R.id.photorequestprogredss);
        bar.setVisibility(View.VISIBLE);
        session=new SessionManagement(rootview.getContext());
        adapter=new photorequestcardadapter(list);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.photorequestlistview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
       return  rootview;
    }
    public void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        HashMap<String, String> user = session.getUserDetails();
        String gen=user.get(SessionManagement.KEY_ID);

        rp.put("fromprofileid",gen);

        client.post(Constants.Request_photo_match,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if(response.getString("res").equals("ok")) {

                        JSONArray ja = response.getJSONArray("data");
                        ids=new String[ja.length()];
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                           String a =jo.getString("fromprofile");
                            fetch(a);
                        }
                    }
                    else
                    {
                        bar.setVisibility(View.INVISIBLE);
                        none.setVisibility(View.VISIBLE);
                    nonetext.setVisibility(View.VISIBLE);
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
                            profileid=jo.getString("profileid");
                            Name =jo.getString("name");
                            imageurl=jo.optString("image");
                            age=jo.optString("age");

                            list.add( new photorequestcarditems(Name,imageurl,profileid,age));
                        }
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
                Toast.makeText(getContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }

}

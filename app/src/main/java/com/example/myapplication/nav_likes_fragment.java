package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class nav_likes_fragment extends Fragment {


    ArrayList<likescarditems> list=new ArrayList<>();
    String[] ids;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Activity context;
    SessionManagement session;
    TextView none;
    ProgressBar bar,bar2;


    @Override
    public void onStart() {
        match();
        super.onStart();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview= inflater.inflate(R.layout.nav_likes,container,false);
        none=(TextView)rootview.findViewById(R.id.likesnone);
        bar=(ProgressBar)rootview.findViewById(R.id.likesprogress);
        Constants constants=new Constants();
        context= (Activity) getContext();
        session=new SessionManagement(rootview.getContext());
        adapter=new likescardadapter(list);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.listviewlikes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
            return rootview;
    }


    private void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        HashMap<String, String> user = session.getUserDetails();
        String gen=user.get(SessionManagement.KEY_ID);
        rp.put("fromprofileid",gen);
        client.post(Constants.LIKES_PROFILEID,rp,new JsonHttpResponseHandler(){
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
                        bar.setVisibility(View.INVISIBLE);
                         none.setVisibility(View.VISIBLE);
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
        client.post(Constants.SHORTLIST_PROFILEID_DATA,rp,new JsonHttpResponseHandler(){
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
                             list.add( new likescarditems(Name,imageurl,profileid,age));
                        }
                        mRecyclerView.setAdapter(adapter);
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
}

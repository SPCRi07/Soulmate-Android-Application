package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class nav_search_fragment extends Fragment  {

        Button search;
        EditText searchbar;
        TextView profid,none;
    ArrayList<searchcarditems> list=new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    Activity context;
    SessionManagement session;
    ProgressBar bar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootview= inflater.inflate(R.layout.nav_search,container,false);
        bar=(ProgressBar)rootview.findViewById(R.id.searchbyidprogress);
        bar.setEnabled(false);
        bar.setVisibility(View.INVISIBLE);
        none=(TextView)rootview.findViewById(R.id.searchnone);
        search=(Button)rootview.findViewById(R.id.navbtnsearch);
        searchbar=(EditText)rootview.findViewById(R.id.search_editext);
        profid=(TextView)rootview.findViewById(R.id.profid);
        context= (Activity) getContext();
        adapter=new searchcardadapter(list);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.searchlistview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        session=new SessionManagement(rootview.getContext());
        mRecyclerView.setVisibility(View.INVISIBLE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchbar.getText().length()!=0)
                {
                    searchbar.setVisibility(View.INVISIBLE);
                    profid.setVisibility(View.INVISIBLE);
                    search.setVisibility(View.INVISIBLE);
                   String abc=searchbar.getText().toString();
                    fetch(abc);
                    bar.setVisibility(View.VISIBLE);
                }
                else
                {
                    searchbar.setError("Enter UserName First");
                }
            }
        });
        return  rootview;

    }


    private void fetch(String profileid){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("username",profileid);
        client.post(Constants.SEARCHBAR,rp,new JsonHttpResponseHandler(){
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
                            list.add( new searchcarditems(Name,imageurl,profileid,age));
                            bar.setVisibility(View.INVISIBLE);
                            mRecyclerView.setVisibility(View.VISIBLE);
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

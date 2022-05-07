package com.example.myapplication;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;


public class Tab3profile extends Fragment {
    private SessionManagement sessionManagement;
    TextView phone,photo;
    String name2,statuscode;
    String name3,fromprofile;
   String temp;
    Boolean abc;
    HashMap<String, String> user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.tab3profile, container, false);
        sessionManagement=new SessionManagement(getContext());
         user=  sessionManagement.getUserDetails();
         fromprofile=user.get(SessionManagement.KEY_ID);
        Bundle budle= Objects.requireNonNull(getActivity()).getIntent().getExtras();
        assert budle != null;
        name2=budle.getString("name1");
        name3=budle.getString("name3");
        phone=(TextView)rootview.findViewById(R.id.getphonetext);
        photo=(TextView)rootview.findViewById(R.id.getphototext);
       abc= sessionManagement.isPremium();
       phone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(abc){
                    fetchnum();
                }
                else
                {
                    phone.setText("Please Purchase Membership to Unlock this feature ");
                    phone.setTextColor(Color.RED);
                    photo.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);

                }
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(abc){
                    fetch2();
                }
                else
                {
                    photo.setText("Please Purchase Membership to Unlock this feature ");
                    photo.setTextColor(Color.RED);
                    photo.setBackgroundTintMode(PorterDuff.Mode.LIGHTEN);

                }
            }
        });

        return rootview;
    }

    private void fetch2(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        statuscode=user.get(SessionManagement.Premium_code);
        final int i=Integer.parseInt(statuscode);
        temp=String.valueOf(i);
        rp.put("toprofileid",name2);
        rp.put("fromprofileid",fromprofile);
        rp.put("statuscode",temp);

        client.post(Constants.Request_photo,rp,new JsonHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {
                        photo.setText("Request Sended to "+name3);
                        photo.setTextColor(Color.BLUE);
                     }
                    if (response.getString("res").equals("failed")) {
                        photo.setText("You can't Request Your Quota is Full Please RENEW Subscription");
                        photo.setTextColor(Color.RED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fetchnum(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        statuscode=user.get(SessionManagement.Premium_code);
        final int i=Integer.parseInt(statuscode);
        temp=String.valueOf(i);
        rp.put("toprofileid",name2);
        rp.put("fromprofileid",fromprofile);
        rp.put("statuscode",temp);


        client.post(Constants.Request_number,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {
                        phone.setText(uservalues.mobilenum);
                        phone.setTextColor(Color.BLUE);
                    }
                    if (response.getString("res").equals("failed")) {
                        phone.setText("You can't Request Your Quota is Full Please RENEW Subscription");
                        phone.setTextColor(Color.RED);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}


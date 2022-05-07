package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;


public class Tab2profile extends Fragment {

    private TextView n1,c1,id1,cast,religion,employee,education,occupation;
    String name2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=  inflater.inflate(R.layout.activity_family, container, false);
        Bundle budle= Objects.requireNonNull(getActivity()).getIntent().getExtras();
        assert budle != null;
        name2=budle.getString("name1");
        n1=(TextView)rootview.findViewById(R.id.textfamilytype);
        c1=(TextView)rootview.findViewById(R.id.textfamilyvalues);
        id1=(TextView)rootview.findViewById(R.id.textfamilystatus);
        cast=(TextView)rootview.findViewById(R.id.textfathersoccupation);
        religion=(TextView)rootview.findViewById(R.id.textmothersoccupation);
        employee=(TextView)rootview.findViewById(R.id.textbrothre);
        education=(TextView)rootview.findViewById(R.id.textsister);
        occupation=(TextView)rootview.findViewById(R.id.textfamilybasedoutfor);
        fetch(name2);
        return rootview;
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
                            n1.setText(jo.getString("familytype"));
                            c1.setText(jo.optString("familyvalue"));
                            id1.setText(jo.optString("familystatus"));
                            cast.setText(jo.optString("fatheroccupation"));
                            religion.setText(jo.optString("motheroccupation"));
                            employee.setText(jo.optString("brother"));
                            education.setText(jo.optString("sister"));
                            occupation.setText(jo.optString("familybasedof"));
                        }

                      }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}


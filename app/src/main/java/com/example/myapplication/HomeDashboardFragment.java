package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;

import static android.app.Activity.RESULT_OK;

public class HomeDashboardFragment extends Fragment  {

    private TextView id;
    private TextView name1;
    private TextView city1;
    private SessionManagement session;
    private TextView a1;
    private String Name;
    private String imageurl;
    private RecyclerView mRecyclerView;
    private Activity context;
    private EditText search;
    ImageView search2,searchclear;
    RecyclerView.Adapter adapter;
    ArrayList<listcarditems> list=new ArrayList<>();
    listcardadapter listcardadapter1;
    ImageView userimage;
    private String profile,gender;
    private CardView c1,c2,c3,c4;
    Context con;
    private TextView newmatches,interestsend,profileview,shortlisted,numofnums,numofphoto,numoflikes;
    HashMap<String, String> user;
    String a,b,c,d,unlock,pro,e,f,k,Gender;
    int REQ_CODE_SPEECH_INPUT=2;
    Intent intent1;
    ArrayList<String> result;
    @Override
    public void onStart() {
        match(user.get(SessionManagement.USER_CITY));
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home_dashboard, container, false);
        session = new SessionManagement(rootview.getContext());
           user= session.getUserDetails();
        context = (Activity) getContext();
        con= rootview.getContext();
        search= rootview.findViewById(R.id.matchestext);
        search2= rootview.findViewById(R.id.searchbyvoice);
        searchclear= rootview.findViewById(R.id.clearsearch);
        Gender=user.get(SessionManagement.Gender);
        name1 = (TextView) rootview.findViewById(R.id.username);
        city1 = (TextView) rootview.findViewById(R.id.userinfo);
        a1 = (TextView) rootview.findViewById(R.id.nametext1);
        adapter = new listcardadapter(list);
        listcardadapter1=new listcardadapter(con,list);
        c1 = (CardView) rootview.findViewById(R.id.namecard1);
        c2 = (CardView) rootview.findViewById(R.id.card5);
        c3 = (CardView) rootview.findViewById(R.id.card6);
        c4 = (CardView) rootview.findViewById(R.id.card8);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        id = (TextView) rootview.findViewById(R.id.userinfo2);
        userimage = (ImageView) rootview.findViewById(R.id.userimage);
        name1.setText(user.get(SessionManagement.Name));
        id.setText(user.get(SessionManagement.Profileid));
        city1.setText(user.get(SessionManagement.usercity));
        String image = user.get(SessionManagement.Image);

        city1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"login to see profile",Toast.LENGTH_SHORT).show();
            }
        });
        if(Gender.matches("Female")){
            unlock=Constants.lockimageurlboy;
        Picasso.with(context).load(image).error(R.drawable.ic_boy).into(userimage);}
        else{
            Picasso.with(context).load(image).error(R.drawable.ic_girl).into(userimage);
            unlock=Constants.lockimageurlgirl;
        }
        searchclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.getText().clear();
                searchclear.setVisibility(View.INVISIBLE);
            }
        });
        newmatches=(TextView) rootview.findViewById(R.id.newmatchescard);
        interestsend=(TextView) rootview.findViewById(R.id.numofinterest);
        profileview=(TextView) rootview.findViewById(R.id.numofprofileview);
        shortlisted=(TextView) rootview.findViewById(R.id.numofshortlisted);
        numofnums=(TextView) rootview.findViewById(R.id.numofnumunlock);
        numofphoto=(TextView) rootview.findViewById(R.id.numofphotorequnlock);
        numoflikes=(TextView) rootview.findViewById(R.id.numoflikes);
        gender=user.get(SessionManagement.Gender);
        if(session.isPremium()){
            c2.setVisibility(View.VISIBLE);
            c3.setVisibility(View.VISIBLE);
        }
        else
        {
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
        }

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchclear.setVisibility(View.VISIBLE);
               listcardadapter1.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return rootview;
    }

    public void match(String city){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp= new RequestParams();
        final String gen=session.getUserDetails().get(SessionManagement.USER_GENDER);
        String id=session.getUserDetails().get(SessionManagement.Profileid);
        rp.put("city",city);
        rp.put("gender",gen);
        rp.put("id",id);

        client.post(Constants.MATCH_WITH_CITY_URL,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    int matchlength=0;
                    Boolean avc=false;
                    if(response.getString("res").equals("ok")) {
                             JSONArray ja = response.getJSONArray("data");
                             JSONArray ja1 = response.getJSONArray("data1");
                               matchlength=ja.length();

                               if(!ja1.get(0).equals("true")){
                                   avc=true;
                               }
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo=ja.getJSONObject(i);
                            profile=jo.getString("profileid");
                            Name =jo.getString("name");
                           imageurl=jo.optString("image");
                           pro=jo.getString("photolock");
                           a= response.getString("totalmatches");
                            b=response.getString("profileview");
                            c=response.getString("shortlist");
                           d= response.getString("totalrequest");
                           e= response.getString("totalnumused");
                           f= response.getString("totalphotoused");
                            k=response.getString("receivedlikes");
                           if (pro.matches("true")) {
                                if(avc){
                                        list.add(new listcarditems(Name, imageurl, profile));
                                      }
                                else{
                                       list.add(new listcarditems(Name, unlock, profile));
                                   }
                           }
                           else
                           {
                                list.add(new listcarditems(Name, imageurl, profile));
                           }
                        }
                        newmatches.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        interestsend.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        profileview.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        shortlisted.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        numofnums.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        numofphoto.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        numoflikes.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                        newmatches.setText(matchlength+"                         New Matches");
                        interestsend.setText(k+"                         Likes Received");
                        profileview.setText( b+"                  ProfileViews");
                        shortlisted.setText(c+"                         Shortlisted");
                        numofnums.setText(e+"                         numunlock used");
                        numofphoto.setText(f+"                         photounlock used");
                        numoflikes.setText( a+"                           Matches");
                    }
                }
                catch (JSONException ignored)
                { }
                mRecyclerView.setAdapter(listcardadapter1);
                search.setVisibility(View.VISIBLE);
                search.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
                search2.setVisibility(View.VISIBLE);
                search2.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtition));
            }
        });

    }
    public void startListening() {
        //Intent to listen to user vocal input and return result in same activity
        intent1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Use a language model based on free-form speech recognition.
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //Message to display in dialog box
        intent1.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.speechgetstring));
        try {
            startActivityForResult(intent1, REQ_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException ignored)
        {}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK && null != data) {
            }
            if (data != null) {
                result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                search.setText(result.get(0));
            }
        }
    }
}



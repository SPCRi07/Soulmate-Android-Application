package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class profileview extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    TabLayout.Tab tab1,tab2,tab3;
    String name2;
    ImageView photo;
    ImageView shortlist,request,like;
    SessionManagement sessionManagement;
    String toprofile,myprofile;
    ImageView back;
    HashMap<String,String> user;
    String pro;
    String profile,unlock;
    String pho;
    String gender;
    AnimatedVectorDrawable avd;
    AnimatedVectorDrawableCompat avd2;
    Drawable drawable;
    Constants constants;
    Boolean tts=false;
    String gen;
    int gen2;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profileview);
        Bundle budle= getIntent().getExtras();
        assert budle != null;
        name2=budle.getString("name1");
        constants=new Constants();
        sessionManagement=new SessionManagement(getApplicationContext());
        user=sessionManagement.getUserDetails();
        myprofile=user.get(SessionManagement.KEY_ID);
        tabLayout = (TabLayout)findViewById(R.id.tabLayoutprofile);
        viewPager = (ViewPager)findViewById(R.id.pagerprofile);
        back=(ImageView)findViewById(R.id.backtohome);
        photo=(ImageView)findViewById(R.id.profileviewimage);
        like=(ImageView)findViewById(R.id.profilelike);
        drawable=like.getDrawable();
        tab1=tabLayout.newTab().setText("About");
        tab2=tabLayout.newTab().setText("Family");
        tab3=tabLayout.newTab().setText("Contact");
          tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        profilePager adapter = new profilePager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(this);
        request=(ImageView)findViewById(R.id.sendinterest);
        shortlist=(ImageView) findViewById(R.id.addtoshortlist);
        shortlist.hasOnClickListeners();
        gender=user.get(SessionManagement.Gender);



        if(gender.equals("Male")){
            gen=Constants.lockimageurlgirl;
        }
        else
        {
            gen=Constants.lockimageurlboy;
        }
        if(gender.equals("Male")){
            gen2=R.drawable.ic_girl;
        }
        else
        {
            gen2=R.drawable.ic_boy;
        }
       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profileview.this,BottomNavigationActivity.class);
                startActivity(i);
            }
        });
        shortlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch2();
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch3();
            }
        });
        fetch(name2,myprofile);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
       viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void fetch(String name,String namenext){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        final String abc=sessionManagement.getUserDetails().get(SessionManagement.KEY_ID);
        rp.put("profileid",name);
        rp.put("fromprofileid",namenext);

        client.post(Constants.FETCH_URL_PROFILEID_altered,rp,new JsonHttpResponseHandler() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            profile = jo.getString("profileid");
                            toprofile = profile;
                            pho = jo.optString("image");
                            pro = jo.getString("photolock");
                        }

                        if (pro.matches("true")) {
                                Picasso.with(getApplicationContext()).load(gen).error(R.drawable.ic_user).into(photo);}
                         if(pho.matches("null")) {
                                Picasso.with(getApplicationContext()).load(pho).error(gen2).into(photo);
                        }
                        else if(!pro.matches("true")) {
                            Picasso.with(getApplicationContext()).load(pho).error(R.drawable.ic_user).into(photo);
                        }

                        SharedPreferences sharedpreferences = getSharedPreferences("selecteduser", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("profileid", profile);
                        editor.apply();
                    }

                            if (response.optString("res1").equals("ok")) {
                                JSONArray ja1 = response.optJSONArray("data1");
                                for (int i = 0; i < ja1.length(); i++) {
                                    JSONObject jo1 = ja1.optJSONObject(i);
                                    unlock = jo1.optString("status");
                                }

                                if (pro.matches("true") && unlock.matches("accept")) {
                                    tts=true;
                                    Picasso.with(getApplicationContext()).load(pho).error(R.drawable.ic_user).into(photo);
                                }
                            }
                    if(pho.equals("null") || pho.equals(gen) ||
                            (pro.matches("true") && pho.equals(gen))
                            || ((pro.matches("true") && !tts)))
                    { }
                    else {
                        photo.setOnClickListener(new DoubleClickListener() {
                            @Override
                            public void onSingleClick(View v) {
                            }

                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onDoubleClick(View v) {
                                like.setAlpha(0.70f);
                                if (drawable instanceof AnimatedVectorDrawableCompat) {
                                    avd2 = (AnimatedVectorDrawableCompat) drawable;
                                    avd2.start();
                                    constants.addtolikes(toprofile, abc);
                                } else if (drawable instanceof AnimatedVectorDrawable) {
                                    avd = (AnimatedVectorDrawable) drawable;
                                    avd.start();
                                    constants.addtolikes(toprofile, abc);
                                }
                            }
                        });
                    }
                }
                catch (JSONException ignored) { }
            }
        });

    }
    private void fetch2(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        Bundle budle= getIntent().getExtras();
        assert budle != null;
       String abc=sessionManagement.getUserDetails().get(SessionManagement.KEY_ID);
        rp.put("toprofileid",toprofile);
        rp.put("fromprofileid",abc);

        client.post(Constants.ADD_TO_SHORTLIST,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(), "Added to shortlist", Toast.LENGTH_SHORT).show();
                    }
                    if (response.getString("res").equals("failed")) {
                        Toast.makeText(getApplicationContext(), "Already Added to shortlist", Toast.LENGTH_SHORT).show();
                        shortlist.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else
                        {
                        Toast.makeText(getApplicationContext(), "FAILED TO Add to Shortlist", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ignored) {

                }

            }
        });
    }

    private void fetch3(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        Bundle budle= getIntent().getExtras();
        assert budle != null;
        String abc=sessionManagement.getUserDetails().get(SessionManagement.KEY_ID);
        rp.put("toprofileid",toprofile);
        rp.put("fromprofileid",abc);

        client.post(Constants.ADD_TO_REQUEST,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(), "Interest Send Successfully", Toast.LENGTH_SHORT).show();
                    }
                    if (response.getString("res").equals("failed")) {
                        Toast.makeText(getApplicationContext(), "Already Interest sended to "+toprofile, Toast.LENGTH_SHORT).show();
                        request.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        Toast.makeText(getApplicationContext(), "FAILED TO send Interest", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ignored) {
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        tabLayout.selectTab(tabLayout.getTabAt(position));
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    
    }

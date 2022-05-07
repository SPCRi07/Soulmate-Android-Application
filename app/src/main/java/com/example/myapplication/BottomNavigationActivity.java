package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class BottomNavigationActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    boolean doubleBackToExitPressedOnce = false;
    TextView a1,a2,a3,a4;
    CircleImageView photo;
    SessionManagement session;
    HashMap<String, String> user;
    ProgressBar progressBar;
    String premiumcode,id;
    @Override
    protected void onStart() {
        if(isNetworkAvailable(getApplicationContext())){
        }
        else
        {
            LoadingDialog dialog=new LoadingDialog(BottomNavigationActivity.this);
            dialog.startLoadingDialog("Please Turn On Your Internet Connection","Ok");
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        session=new SessionManagement(getApplicationContext());
        user = session.getUserDetails();
        progressBar=(ProgressBar)findViewById(R.id.progressBarNav);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
       menu = navigationView.getMenu();
        /*------------------------Navigation Drawer Menu-------------------*/
        navigationView.bringToFront();
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeDashboardFragment()).commit();
        fetch();
        premiumcode=user.get(SessionManagement.Premium_code);
        id=user.get(SessionManagement.Profileid);
        String gender=user.get(SessionManagement.Gender);
        if(gender.matches("Male")){
            MenuItem i=menu.findItem(R.id.nav_aboutUs);
            i.setIcon(R.drawable.genmale);
        }
        else
        {
            MenuItem i=menu.findItem(R.id.nav_aboutUs);
            i.setIcon(R.drawable.genfemale);
        }
        if(session.isdark()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case  R.id.home:
                    selectedFragment=new HomeDashboardFragment();
                    break;
                case  R.id.search_bar:
                    selectedFragment=new SearchDashboardFragment();
                    break;
                case  R.id.chatbox:
                    selectedFragment=new ChatboxDashboardFragment();
                    break;
                case  R.id.notification:
                    selectedFragment=new NotificationDashboardFragment();
                    break;
                case  R.id.Settings:
                    selectedFragment=new SettingsDashboardFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
            return;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Press Back to Exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeDashboardFragment()).commit();
                 break;
            case R.id.nav_faq:
                Intent i22=new Intent(BottomNavigationActivity.this,nav_Faq.class);
                startActivity(i22);
                break;
            case R.id.nav_matches:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_match_fragment()).commit();
                break;

            case R.id.nav_profilevisitor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_profilevisitor_fragment()).commit();
                break;

            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_search_fragment()).commit();
                break;

            case R.id.nav_shortlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_shortlist_fragment()).commit();
                break;
            case R.id.nav_successsotry:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_successstory_fragment()).commit();
                break;
            case R.id.nav_buy_membership:
                Intent i=new Intent(BottomNavigationActivity.this,payment_gateway.class);
                startActivity(i);
               break;

            case R.id.nav_feedback:
                Intent i2=new Intent(BottomNavigationActivity.this,Feedback.class);
                startActivity(i2);
                break;
            case R.id.nav_aboutUs:
                Intent i1=new Intent(BottomNavigationActivity.this,AboutSoulMate.class);
                startActivity(i1);
                break;
            case R.id.nav_likes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new nav_likes_fragment()).commit();
                break;

                case R.id.nav_renew_membership:
                    Intent i4 = new Intent(BottomNavigationActivity.this, payment_gateway.class);
                    startActivity(i4);
                        break;
            case R.id.nav_meet:
                Intent i6 = new Intent(BottomNavigationActivity.this, city_find.class);
                startActivity(i6);
                break;
                   }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


  public void fetch(){
       AsyncHttpClient client = new AsyncHttpClient();
       RequestParams rp = new RequestParams();
       String abc=user.get(SessionManagement.KEY_EMAIL);
       rp.put("profileid",abc);
       client.post(Constants.FETCH_URL_with_progress,rp,new JsonHttpResponseHandler(){
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               super.onSuccess(statusCode, headers, response);
               String name,id1,image1;

               try {
                   if(response.getString("res").equals("ok")){
                       JSONArray ja=response.getJSONArray("data");
                       a1=(TextView)findViewById(R.id.profilenamehere);
                       a2=(TextView)findViewById(R.id.profileusername);
                       photo=(CircleImageView) findViewById(R.id.profileimagehere);
                       a3=(TextView)findViewById(R.id.checkprime);
                       a4=(TextView)findViewById(R.id.progresscompletioninper);
                       progressBar=(ProgressBar)findViewById(R.id.progressBarNav);
                       for(int i=0;i<ja.length();i++){
                           JSONObject jo=ja.getJSONObject(i);
                           name=jo.optString("name");
                           id1=jo.optString("profileid");
                           image1=jo.optString("image");
                           int progress=response.getInt("length");
                           progressBar.setProgress(progress);
                           a1.setText(name);
                           a2.setText(id1);
                           Picasso.with(getApplicationContext()).load(image1).error(R.drawable.ic_user).into(photo);
                           String p=Integer.toString(progress);
                           a4.setText(" "+p+"/100");
                       }
                       if (session.isPremium()) {
                           a3.setVisibility(View.VISIBLE);
                           MenuItem i=menu.findItem(R.id.nav_renew_membership);
                           MenuItem i2=menu.findItem(R.id.nav_buy_membership);
                           i.setVisible(true);
                           i2.setVisible(false);
                       }
                       if(session.isLike()){
                           MenuItem i3=menu.findItem(R.id.nav_likes);
                           i3.setVisible(true);
                       }
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
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.d("NetworkCheck", "isNetworkAvailable: No");
            return false;
        }
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info != null) {        // iterate through the interfaces
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("NetworkCheck", "isNetworkAvailable: Yes");
                    return true;
                }
            }

        }
        return false;
    }
}

package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.*;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {
    Button login1;
    TextView passf;
    TextView signupp;
    EditText a1, a2;
    String b1, b2;
    SessionManagement session;
    SharedPreferences preferences;
    public ProgressDialog progressDialog;
    public static String user1;
    SwipeRefreshLayout refreshLayout;
    LoadingDialog dialog;
    ImageButton mic;
    int REQ_CODE_SPEECH_INPUT=2;
    Intent intent1;
    ArrayList<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        session = new SessionManagement(getApplicationContext());
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refreshlogin);
        dialog=new LoadingDialog(login.this);
        Toast.makeText(getApplicationContext(),"guest User Logging in",Toast.LENGTH_LONG).show();
        mic=(ImageButton)findViewById(R.id.miclogin);
        if (session.isLoggedIn()) {
            Intent intent = new Intent(login.this, BottomNavigationActivity.class);
            startActivity(intent);
        }
        if(isNetworkAvailable(getApplicationContext())){
        }
        else
        {
            dialog.startLoadingDialog("Can't Connect to Internet");
         }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkAvailable(getApplicationContext())){
                    dialog.dismissDialog();
                }
                else
                {
                    dialog.startLoadingDialog("Can't Connect to Internet");
                }

                refreshLayout.setRefreshing(false);

            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening();
            }
        });

        a1 = (EditText) findViewById(R.id.email);
        login1 = (Button) findViewById(R.id.login);
        session.isLoggedIn();
        login1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                a2 = (EditText) findViewById(R.id.pass);
                b1 = a1.getText().toString();
                b2 = a2.getText().toString();
                if (b1.matches("")) {
                    a1.setError("Enter EmailID");
                }
                if (b2.matches("")) {
                    a2.setError("Enter Password");
                } else if (!b1.matches("") && !b2.matches("")) {
                    Login(a1.getText().toString(), a2.getText().toString());
                    progressDialog = ProgressDialog.show(login.this, "Checking Credentials", "Please Wait", false, false);
                }
            }
        });
        passf = (TextView) findViewById(R.id.passforgot);

        passf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.myapplication.login.this, forgotpass.class);
                startActivity(i);
            }
        });
        signupp = (TextView) findViewById(R.id.textsignup);

        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.myapplication.login.this, reg1.class);
                startActivity(i);

            }
        });
    }

    public void Login(final String username, String password) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("username", username);
        rp.put("password", password);
        client.post(Constants.Base_Url, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if (response.getString("res").equals("ok")) {
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(0);
                            String profileid = jo.getString("profileid");
                            String email = jo.getString("emailid");
                            String city = jo.getString("city");
                            String gender = jo.getString("gender");
                            String image = jo.optString("image");
                            String name = jo.getString("name");
                            premium(profileid);
                            preferences = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);
                            SharedPreferences.Editor edit = preferences.edit();
                            user1 = username;
                            edit.putString("gender", gender);
                            edit.apply();
                            session.createLoginSession(email, profileid);
                            session.userdata(city);
                            session.userdataSelected(gender);
                            session.fulluserdata(profileid, email, image, city, gender, name);
                            Toast.makeText(com.example.myapplication.login.this, "WELCOME " + username, Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
                        finish();
                    } else {
                        Toast.makeText(login.this, "Wrong Credentials Enter Correct", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(com.example.myapplication.login.this, statusCode + responseString, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void premium(String profileid) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid", profileid);
        client.post(Constants.Check_Subscription, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    if (response.getString("res").equals("ok")) {
                        String abc, status;
                        JSONArray ja = response.getJSONArray("data");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            abc = jo.getString("subscriptioncode");
                            status = jo.getString("subscriptionstatus");
                            if (status.matches("true")) {
                                session.createpremiumcode(abc);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(), statusCode + responseString, Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK && null != data) {
             }
                if (data != null) {
                    result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    a1.setText(result.get(0));
                }
            }
            }
}

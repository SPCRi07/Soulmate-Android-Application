package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMystatisticsBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class mystatistics extends AppCompatActivity {



ActivityMystatisticsBinding binding;
    SessionManagement session;
    HashMap<String, String> user;
    String a1,b1,c1,d1;
    int a,z,x,c,v,b,n,m,s,d,f,g,h,j,k,l,p;
    String totalmatches,yoursrequestacceptedbyothers,byyourequestaccepted,yourprofileview,yourrequestrejected,
            yourrequestpending, yourrequestrejcted,  photolockrejected,photolockaccept,photolockpending,numlock,
            shortlist,photolockused,numlockused;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMystatisticsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        session = new SessionManagement(getApplicationContext());
        user= session.getUserDetails();
        a1=user.get(SessionManagement.Name);
        b1=user.get(SessionManagement.KEY_EMAIL);
        c1=user.get(SessionManagement.Profileid);

        if(session.isPremium()) {
            d1 = user.get(SessionManagement.Premium_code);
        }
        else{
            d1="0";
        }
        binding.textview1.setText("Your Name:- "+a1);
        binding.textView2.setText("Your Emailid:- "+b1);
        binding.textView3.setText("Your Profileid:- "+c1);
        binding.backbuttonmystatics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mystatistics.this,BottomNavigationActivity.class);
                startActivity(i);
            }
        });
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams rp = new RequestParams();
            final int i=Integer.parseInt(d1);
            String temp=String.valueOf(i);
            rp.put("profileid",c1);
            rp.put("code",temp);

            client.post(Constants.Get_Usage_Info,rp,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        a=response.getInt("totalmatches");
                        z=response.getInt("yoursrequestacceptedbyothers");
                        x=response.getInt("byyourequestaccepted");
                        c=response.getInt("yourprofileview");
                        v=response.getInt("yourrequestrejected");
                        b=response.getInt("yourrequestpending");
                        n=response.getInt("yourrequestrejcted");
                        m=response.getInt("photolockrejected");
                        s=response.getInt("photolockaccept");
                        d=response.getInt("photolockpending");
                        f=response.getInt("numlock");
                        g=response.getInt("shortlist");
                        h=response.optInt("photolockused");
                        j=response.optInt("numlockused");

                        totalmatches=inttostring(a);
                        yoursrequestacceptedbyothers=inttostring(z);
                        byyourequestaccepted=inttostring(x);
                        yourprofileview=inttostring(c);
                        yourrequestrejected=inttostring(v);
                        yourrequestpending=inttostring(b);
                        yourrequestrejcted=inttostring(n);
                        photolockrejected=inttostring(m);
                        photolockaccept=inttostring(s);
                        photolockpending=inttostring(d);
                        numlock=inttostring(f);
                        shortlist=inttostring(g);
                        photolockused=inttostring(h);
                        numlockused=inttostring(j);

                        binding.textview4.setText("totalmatches:- "+totalmatches);
                        binding.textview5.setText("yoursrequestacceptedbyothers:- "+yoursrequestacceptedbyothers);
                        binding.textview6.setText("byyourequestaccepted:- "+byyourequestaccepted);
                        binding.textview7.setText("yourprofileview:- "+yourprofileview);
                        binding.textview8.setText("yourrequestrejected:- "+yourrequestrejected);
                        binding.textview9.setText("yourrequestpending:- "+yourrequestpending);
                        binding.textview10.setText("yourrequestrejcted:- "+yourrequestrejcted);
                        binding.textview11.setText("shortlist:- "+shortlist);

                        if(session.isPremium()){
                        binding.textview15.setText("photolockrejected:- "+photolockrejected);
                        binding.textview12.setText("photolockaccept:- "+photolockaccept);
                        binding.textview13.setText("photolockpending:- "+photolockpending);
                        binding.textview14.setText("numlock:- "+numlock);
                        binding.textview16.setText("photolockused:- "+photolockused);
                        binding.textview17.setText("numlockused:- "+numlockused);}
                    }

                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Toast.makeText(getApplicationContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
                }
            });
        }
    public String inttostring(int s){
        String s1=Integer.toString(s);
        return s1;

    }

}

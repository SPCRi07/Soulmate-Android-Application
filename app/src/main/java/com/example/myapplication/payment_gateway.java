package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import cz.msebera.android.httpclient.Header;

public class payment_gateway extends Activity implements PaymentResultListener {
    private static final String TAG = "Nothing";
    Button buy;
    SessionManagement sessionManagement;
    String id;
    int dday,dmonth,dyear;
    String dob="";
    String  profileid,email;
    CardView silver,gold,diamond,months;
    TextView selectsilver,selectgold,selectdiamond,select3month,select6month,select12month,selectedplan,totalprice;
    ScrollView packages;
    RelativeLayout about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_premium);
        Checkout.preload(getApplicationContext());
        silver=(CardView)findViewById(R.id.silver);
        gold=(CardView)findViewById(R.id.gold);
        diamond=(CardView)findViewById(R.id.diamond);
        months=(CardView)findViewById(R.id.monthscard);
        packages=(ScrollView)findViewById(R.id.packagescrollbar);
        selectedplan=(TextView)findViewById(R.id.Aboutselectedpackage);
        totalprice=(TextView)findViewById(R.id.Abouttotalprice);
        about=(RelativeLayout)findViewById(R.id.paymentrelative);
        selectsilver=(TextView)findViewById(R.id.silverselect);
        selectgold=(TextView)findViewById(R.id.goldselect);
        selectdiamond=(TextView)findViewById(R.id.diamondselect);
        select3month=(TextView)findViewById(R.id.select3price);
        select6month=(TextView)findViewById(R.id.select6price);
        select12month=(TextView)findViewById(R.id.select12months);
        about.setVisibility(View.INVISIBLE);

        select3month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.INVISIBLE);
                buy.setEnabled(true);
                buy.setVisibility(View.VISIBLE);
                uservalues.packagemonths="3 Months";
                uservalues.nowtime=time();
                uservalues.expirytime=expirytime(3);
                Toast.makeText(getApplicationContext(),uservalues.expirytime,Toast.LENGTH_LONG).show();
                int total=uservalues.packageprice*3;
                uservalues.packagepricetotal= Integer.toString(total);
                about.setVisibility(View.VISIBLE);
                selectedplan.setText(uservalues.packagename);
                totalprice.setText(uservalues.packagepricetotal);
                uservalues.subscriptioncode="3";
            }
        });
        select6month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.INVISIBLE);
                buy.setEnabled(true);
                buy.setVisibility(View.VISIBLE);
                uservalues.packagemonths="6 Months";
                int total=uservalues.packageprice*6;
                uservalues.nowtime=time();
                uservalues.expirytime=expirytime(6);
                Toast.makeText(getApplicationContext(),uservalues.expirytime,Toast.LENGTH_LONG).show();
                uservalues.packagepricetotal= Integer.toString(total);
                about.setVisibility(View.VISIBLE);
                selectedplan.setText(uservalues.packagename);
                totalprice.setText(uservalues.packagepricetotal);
                uservalues.subscriptioncode="6";
            }
        });
        select12month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.INVISIBLE);
                buy.setEnabled(true);
                buy.setVisibility(View.VISIBLE);
                uservalues.packagemonths="12 Months";
                int total=uservalues.packageprice*12;
                uservalues.nowtime=time();
                uservalues.expirytime=expirytime(12);
                Toast.makeText(getApplicationContext(),uservalues.expirytime,Toast.LENGTH_LONG).show();
                uservalues.packagepricetotal= Integer.toString(total);
                about.setVisibility(View.VISIBLE);
                selectedplan.setText(uservalues.packagename);
                totalprice.setText(uservalues.packagepricetotal);
                uservalues.subscriptioncode="12";
            }
        });

        selectsilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.VISIBLE);
                uservalues.packagename="Silver";
                uservalues.packageprice=100;
            }
        });
        selectgold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.VISIBLE);
                uservalues.packagename="gold";
                uservalues.packageprice=200;
            }
        });
        selectdiamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packages.setVisibility(View.INVISIBLE);
                months.setVisibility(View.VISIBLE);
                uservalues.packagename="diamond";
                uservalues.packageprice=300;
            }
        });
        buy = (Button) findViewById(R.id.buy);
        buy.setEnabled(false);
        packages.setEnabled(false);
        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);
        uservalues.fullname=user.get(SessionManagement.Name);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {

        String name=uservalues.fullname;
        String price=uservalues.packagepricetotal;
        String descrption=uservalues.packagename;

        final Activity activity = this;

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_5kqr0l4nc72RN7");

        /**
         * Set your logo here
         */
       checkout.setImage(R.drawable.logo5);

        /**
         * Reference to current activity
         */
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();
            options.put("name", name);
            options.put("description", descrption);
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
          //  options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");
            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", price+"00");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        addtosubscription();
    }
    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

    }
    private String time()
        {
        Calendar c = Calendar.getInstance();
        dday = c.get(Calendar.DAY_OF_MONTH);
        dmonth = c.get(Calendar.MONTH);
        dyear = c.get(Calendar.YEAR);
         String date=(""+dday + "/" + (dmonth+1) + "/" + dyear);
         return date;
        }

    private String expirytime(int month)
    {
        Calendar c = Calendar.getInstance();
        dday = c.get(Calendar.DAY_OF_MONTH);
        dmonth = c.get(Calendar.MONTH);
        dyear = c.get(Calendar.YEAR);
        String date;
        if(month==3){
            c.add(Calendar.MONTH,3);
            int monthadded=c.get(Calendar.MONTH);
            date = ("" + dday + "/" + (monthadded+1) + "/" + dyear);
            return date;
        }
            if(month==6){
            c.add(Calendar.MONTH,6);
            int monthadded=c.get(Calendar.MONTH);
            date = ("" + dday + "/" + (monthadded+1) + "/" + dyear);
            return date;
             }
        if(month==12)
        {
            c.add(Calendar.YEAR, 1);
            int yearadded=c.get(Calendar.YEAR);
            date=(""+dday + "/" + (dmonth + 1) + "/" + yearadded);
            return date;
        }
        else
        {
            return null;
        }
    }

    private void addtosubscription(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",id);
        rp.put("scode",uservalues.subscriptioncode);
        rp.put("sname",uservalues.packagename);
        rp.put("sprice",uservalues.packagepricetotal);
        rp.put("sduration",uservalues.packagemonths);
        rp.put("startdate",uservalues.nowtime);
        rp.put("enddate",uservalues.expirytime);
        client.post(Constants.AddtoSubscription,rp,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.getString("res").equals("ok")) {
                        sessionManagement.createpremiumcode(uservalues.subscriptioncode);
                        Intent i1=new Intent(payment_gateway.this,BottomNavigationActivity.class);
                        startActivity(i1);
                        Toast.makeText(getApplicationContext(),"You are Premium Member now",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Unable to connect Database",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

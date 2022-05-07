package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class partner_pref extends AppCompatActivity {


    ListView listView=null;
    ListView listView1=null;
    ListView listView2=null;
    ListView listView3=null;
    ListView listView4=null;
    ListView listView5=null;
    ListView listView6=null;
    ListView listView7=null;
    ListView listView8=null;
    ListView listView9=null;
    ListView listView10=null;
    ListView listView11=null;
    ListView listView12=null;
    EditText editText,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12,editText13;
    TextView skip;
    public ProgressDialog progressDialog;



    Button btnfinish;
   public String id;

    SharedPreferences sharedpreferences;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_pref);

        skip=(TextView)findViewById(R.id.partnerskip);
        btnfinish=(Button)findViewById(R.id.btnfnishpp);
        editText=(EditText) findViewById(R.id.selectminage);
        editText2=(EditText) findViewById(R.id.selectmaxage);
        editText3=(EditText) findViewById(R.id.selectminheight);
        editText4=(EditText) findViewById(R.id.selectmaxhieght);
        editText5=(EditText) findViewById(R.id.selectmanglik);
        editText6=(EditText) findViewById(R.id.selectmaritialstatus);
        editText7=(EditText) findViewById(R.id.selectreligion);
        editText8=(EditText) findViewById(R.id.selectcast);
        editText9=(EditText) findViewById(R.id.selectmothertongue);
        editText10=(EditText) findViewById(R.id.selecteducation);
        editText11=(EditText) findViewById(R.id.selectoccupation);
        editText12=(EditText) findViewById(R.id.selectminincome);
        editText13=(EditText) findViewById(R.id.selectmaxincome);
        sharedpreferences= getSharedPreferences("username", Context.MODE_PRIVATE);
        id= sharedpreferences.getString("id",null);

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    uservalues.selectminage=editText.getText().toString();
                    uservalues.selectmaxage=editText2.getText().toString();
                    uservalues.selectminheight=editText3.getText().toString();
                    uservalues.selectmaxheight=editText4.getText().toString();
                    uservalues.selectmaritialstatus=editText6.getText().toString();
                    uservalues.selectreligion=editText7.getText().toString();
                    uservalues.selectcast=editText8.getText().toString();
                    uservalues.selectmothertongue=editText9.getText().toString();
                    uservalues.selecteducation=editText10.getText().toString();
                    uservalues.selectoccupation=editText11.getText().toString();
                    uservalues.selectminincome=editText12.getText().toString();
                    uservalues.selectmaxincome=editText13.getText().toString();
                progressDialog= ProgressDialog.show(partner_pref.this,"Storing Your SOULMATE","Please Wait",false,false);

                lookingfor();
            }
        });



        listView=new ListView(this);
        listView1=new ListView(this);
        listView2=new ListView(this);
        listView3=new ListView(this);
        listView4=new ListView(this);
        listView5=new ListView(this);
        listView6=new ListView(this);
        listView7=new ListView(this);
        listView8=new ListView(this);
        listView9=new ListView(this);
        listView10=new ListView(this);
        listView11=new ListView(this);
        listView12=new ListView(this);


        final String[] item={"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37" ,"38","39","40"};

        final String[] item1={"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37" ,"38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59" ,"60","61","62","63","64","65","66","67" ,"68","69","70","70"};

        final String[] item2={"4 0 feet","4 1 feet","4 2 feet","4 3 feet","4 4 feet","4 5 feet","4 6 feet","4 7 feet","4 8 feet","4 9 feet","5 0 feet","5 1 feet","5 2 feet","5 3 feet","5 4 feet","5 5 feet","5 6 feet","5 7 feet","5 8 feet","59 feet" ,"6 0 feet","6 1 feet","6 2 feet","6 3 feet","6 4 feet","6 5 feet","6 6 feet","6 7 feet","6 8 feet","6 9 feet","7 0 feet","7 0 feet or above"};

        final String[] item3={"4 0 feet","4 1 feet","4 2 feet","4 3 feet","4 4 feet","4 5 feet","4 6 feet","4 7 feet","4 8 feet","4 9 feet","5 0 feet","5 1 feet","5 2 feet","5 3 feet","5 4 feet","5 5 feet","5 6 feet","5 7 feet","5 8 feet","59 feet" ,"6 0 feet","6 1 feet","6 2 feet","6 3 feet","6 4 feet","6 5 feet","6 6 feet","6 7 feet","6 8 feet","6 9 feet","7 0 feet","7 0 feet or above"};

        final String[] item4={"Doesnt Matter","Manglik","Non Manglik","partial Manglik"};

        final String[] item5={"Doesnt Matter","Never Married","Married","Awaiting Divorce","Divorced","Widowed"};

        final String[] item6={"Doesnt Matter","Hindu","Muslim","Sikh","Jain","Parsi","Christian","Jawish","Bahai","Others"};

        final String[] item7={"Doesnt Matter","Agri","Bhavsar","Dhobi","Gatti","Gopan","Arora","Jat","Kalal","Kapu","Kohli","Maru","Nair","Nepali","Patel","Rajput","Raval","Reddy","Nair","Rathod","Rao","Sharma","Suthar","Sheth","Soni","Thakor","Thakkar","Trivedi","Vyas","Vanand","Yadav","Others"};

        final String[] item8={"Doesnt Matter","Hindi","Panjabi","Haryanvi","Himachali","Sindhi","Marathi","Gujarati","Malayalam","Tamil","Telugu","Bengali","English","Kannada","Others"};

        final String[] item9={"Doesnt Matter","B.Arch","B.E/B.Tech","B.Pharma","M.E/M.Tech","M.Pharma","M.S","B.IT","BCA","MCA","B.Com","M.Com","CA","BBA","MBA","BAMS","BHMS","MBBS","M.Com","MDS","LLB" ,"PHD","High School","Diploma","Others"};

        final String[] item10={"Doesnt Matter","Looking for job","Not working","Retired","Student","Other"};

        final String[] item11={"Doesnt Matter","No Income","Rs.0-1 Lakh","Rs.1-1 Lakh","Rs.2-3 Lakh","Rs.3-4 Lakh","Rs.4-5 Lakh","Rs.5-6 Lakh","Rs.10-15 Lakh","Rs.15-20 Lakh","Rs.20-40 Lakh","Rs.40-70 Lakh","Rs.70-1 crore","Rs. 1 crore & above"};

        final String[] item12={"Doesnt Matter","No Income","Rs.0-1 Lakh","Rs.1-1 Lakh","Rs.2-3 Lakh","Rs.3-4 Lakh","Rs.4-5 Lakh","Rs.5-6 Lakh","Rs.10-15 Lakh","Rs.15-20 Lakh","Rs.20-40 Lakh","Rs.40-70 Lakh","Rs.70-1 crore","Rs. 1 crore & above"};



        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item);
        listView.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item1);
        listView1.setAdapter(arrayAdapter1);


        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item2);
        listView2.setAdapter(arrayAdapter2);


        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item3);
        listView3.setAdapter(arrayAdapter3);


        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item4);
        listView4.setAdapter(arrayAdapter4);


        ArrayAdapter<String> arrayAdapter5=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item5);
        listView5.setAdapter(arrayAdapter5);


        ArrayAdapter<String> arrayAdapter6=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item6);
        listView6.setAdapter(arrayAdapter6);


        ArrayAdapter<String> arrayAdapter7=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item7);
        listView7.setAdapter(arrayAdapter7);



        ArrayAdapter<String> arrayAdapter8=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item8);
        listView8.setAdapter(arrayAdapter8);


        ArrayAdapter<String> arrayAdapter9=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item9);
        listView9.setAdapter(arrayAdapter9);


        ArrayAdapter<String> arrayAdapter10=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item10);
        listView10.setAdapter(arrayAdapter10);


        ArrayAdapter<String> arrayAdapter11=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item11);
        listView11.setAdapter(arrayAdapter11);


        ArrayAdapter<String> arrayAdapter12=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item12);
        listView12.setAdapter(arrayAdapter12);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText.setText(txt1);
                recreate();
            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText2.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText3.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });


        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText4.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText5.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });

        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText6.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });


        listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText7.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });

        listView7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText8.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });


        listView8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText9.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);

            }
        });


        listView9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText10.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);

            }
        });

        listView10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText11.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);

            }
        });


        listView11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText12.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);

            }
        });

        listView12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText13.setText(txt1);
                recreate();
                ((ViewGroup) view).removeView(view);
            }
        });



    }


    public void showDialogListView(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView2(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView1);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView3(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView2);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView4(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView3);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView5(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView4);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView6(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView5);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView7(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView6);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView8(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView7);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView9(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView8);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView10(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView9);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView11(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView10);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView12(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView11);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView13(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(partner_pref.this);
        builder.setCancelable(true);
        builder.setView(listView12);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void lookingfor(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();

        rp.put("profileid", id);
        rp.put("minage", uservalues.selectminage);
        rp.put("maxage", uservalues.selectmaxage);
        rp.put("minheight",uservalues.selectminheight);
        rp.put("maxheight",uservalues.selectmaxheight);
        rp.put("maritialstatus",uservalues.selectmaritialstatus);
        rp.put("religion", uservalues.selectreligion);
        rp.put("cast", uservalues.selectcast);
        rp.put("mothertongue",uservalues.selectmothertongue);
        rp.put("education", uservalues.selecteducation);
        rp.put("occupation", uservalues.selectoccupation);
        rp.put("minincome", uservalues.selectminincome);
        rp.put("maxincome", uservalues.selectmaxincome);

        client.post(Constants.ADD_TO_LOOKINGFOR, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(partner_pref.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(),"ALGORITHM CREATED",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent i=new Intent(partner_pref.this,login.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"ALGORITHM failed"+response+statusCode,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(partner_pref.this,statusCode + responseString,Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }
}



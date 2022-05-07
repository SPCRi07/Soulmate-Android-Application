package com.example.myapplication;

import android.content.Intent;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class updatesocialdetails extends AppCompatActivity {

    ListView listView=null;
    ListView listView1=null;
    ListView listView2=null;
    ListView listView3=null;
    EditText editText,editText2,editText3,editText4;
    Button nextsocialdetails;
    SessionManagement sessionManagement;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatesocialdetails);


        listView=new ListView(this);
        listView1=new ListView(this);
        listView2=new ListView(this);
        listView3=new ListView(this);
        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);



        editText=(EditText) findViewById(R.id.updatesocial);
        editText2=(EditText) findViewById(R.id.updatetongue);
        editText3=(EditText) findViewById(R.id.updatereligion);
        editText4=(EditText) findViewById(R.id.updatecast);
        nextsocialdetails=(Button)findViewById(R.id.updatesocialdetails_page) ;
        nextsocialdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().matches("")){
                    editText.setError("Enter Detail");
                }
                if(editText2.getText().toString().matches("")){
                    editText2.setError("Enter Detail");
                }
                if(editText3.getText().toString().matches("")){
                    editText3.setError("Enter Detail");
                }
                if(editText4.getText().toString().matches("")){
                    editText4.setError("Enter Detail");
                }


                if(!editText.getText().toString().matches("") && !editText2.getText().toString().matches("") && !editText3.getText().toString().matches("") && !editText4.getText().toString().matches("")) {
                    uservalues.maritialstatus=editText.getText().toString();
                    uservalues.mothertongue=editText2.getText().toString();
                    uservalues.religion=editText3.getText().toString();
                    uservalues.cast=editText4.getText().toString();
                    match();
                 }
                }
        });

        final String[] item={"Never Married","Awaiting Divorce","Divorced","Married","Annuled","Others"};

        final String[] item1={"Hindi","Panjabi","Haryanvi","Himachali","Sindhi","Marathi","Gujarati","Malayalam","Tamil","Telugu","Bengali","English","Kannada","Others"};

        final String[] item2={"Hindu","Muslim","Sikh","Jain","Parsi","Christian","Jawish","Bahai","Others"};

        final String[] item3={"Agri","Bhavsar","Dhobi","Gatti","Gopan","Arora","Jat","Kalal","Kapu","Kohli","Maru","Nair","Nepali","Patel","Rajput","Raval","Reddy","Nair","Rathod","Rao","Sharma","Suthar","Sheth","Soni","Thakor","Thakkar","Trivedi","Vyas","Vanand","Yadav","Others"};



        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item);
        listView.setAdapter(arrayAdapter);


        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item1);
        listView1.setAdapter(arrayAdapter1);


        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item2);
        listView2.setAdapter(arrayAdapter2);


        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item3);
        listView3.setAdapter(arrayAdapter3);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText.setText(txt1);
                recreate();

                ((ViewGroup) view).removeView(view);
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

    }

    public void showDialogListView(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updatesocialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView2(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updatesocialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView1);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView3(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updatesocialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView2);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView4(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updatesocialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView3);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    private void match(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",id);
        rp.put("maritialstatus",uservalues.maritialstatus);
        rp.put("mothertongue",uservalues.mothertongue);
        rp.put("cast",uservalues.cast);
        rp.put("religion",uservalues.religion);
        client.post(Constants.Update_social,rp,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if(response.getString("res").equals("ok")) {
                        Intent i = new Intent(updatesocialdetails.this, editprofile.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent i = new Intent(updatesocialdetails.this, editprofile.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"FAILED to Update profile",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }



}

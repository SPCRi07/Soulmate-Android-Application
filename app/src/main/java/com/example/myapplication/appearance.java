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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class appearance extends AppCompatActivity {

    EditText editText5,editText6;
    String a5,a6,username;
    Button btnfinish;
    Spinner height;
    String id;
    SharedPreferences sharedpreferences;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        sharedpreferences= getSharedPreferences("username", Context.MODE_PRIVATE);
        editText5=(EditText) findViewById(R.id.selectage);
        editText6=(EditText) findViewById(R.id.selectweight);
        height=(Spinner)findViewById(R.id.selectheight);
          btnfinish=(Button)findViewById(R.id.btnnextaboutappearance);

        final String[] item3={"Less than 4 0 feet","4 0 feet","4 1 feet","4 2 feet","4 3 feet","4 4 feet","4 5 feet","4 6 feet","4 7 feet","4 8 feet","4 9 feet","5 0 feet","5 1 feet","5 2 feet","5 3 feet","5 4 feet","5 5 feet","5 6 feet","5 7 feet","5 8 feet","5 9 feet" ,"6 0 feet","6 1 feet","6 2 feet","6 3 feet","6 4 feet","6 5 feet","6 6 feet","6 7 feet","6 8 feet","6 9 feet","7 0 feet","7 0 feet or above"};
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item3);
        height.setAdapter(arrayAdapter3);
        btnfinish.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uservalues.Height=height.getSelectedItem().toString();
        uservalues.Weight=editText6.getText().toString();
        uservalues.Age=editText5.getText().toString();

        if( !editText5.getText().toString().isEmpty() && !editText6.getText().toString().isEmpty()){
            id= sharedpreferences.getString("id",null);
            signin();
            progressDialog= ProgressDialog.show(appearance.this,"Appearance Catching","Please Wait",false,false);

        }
        else {
            Toast.makeText(getApplicationContext(),"Not filled",Toast.LENGTH_SHORT).show();
        }
    }
});

    }
    public void signin()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("profileid",id);
        rp.put("age",uservalues.Age);
        rp.put("weight",uservalues.Weight);
        rp.put("height",uservalues.Height);

        client.post(Constants.EXTRAS_APPEARANCE_SIGNUP_URL, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent i=new Intent(appearance.this,profileupload.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"not registered"+response+statusCode,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(appearance.this,statusCode + responseString,Toast.LENGTH_SHORT).show();
            }

        });

    }


}

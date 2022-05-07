package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class updateappearance extends AppCompatActivity {

    EditText editText5,editText6,editText;
    String a5,a6,username;
    Button btnfinish;
    Spinner height;
    String id;
    SharedPreferences sharedpreferences;
    public ProgressDialog progressDialog;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateappearance);
        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);
        editText5=(EditText) findViewById(R.id.updateage);
        editText6=(EditText) findViewById(R.id.updateweight);
        editText=(EditText)findViewById(R.id.updateusername);
        height=(Spinner)findViewById(R.id.updateheight);
          btnfinish=(Button)findViewById(R.id.updateappearancepage);
        final String[] item3={"Less than 4 0 feet","4 0 feet","4 1 feet","4 2 feet","4 3 feet","4 4 feet","4 5 feet","4 6 feet","4 7 feet","4 8 feet","4 9 feet","5 0 feet","5 1 feet","5 2 feet","5 3 feet","5 4 feet","5 5 feet","5 6 feet","5 7 feet","5 8 feet","5 9 feet" ,"6 0 feet","6 1 feet","6 2 feet","6 3 feet","6 4 feet","6 5 feet","6 6 feet","6 7 feet","6 8 feet","6 9 feet","7 0 feet","7 0 feet or above"};
        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this, R.layout.custom_dialog,R.id.txtitems,item3);
        height.setAdapter(arrayAdapter3);
        btnfinish.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uservalues.Height=height.getSelectedItem().toString();
        uservalues.Weight=editText6.getText().toString();
        uservalues.Age=editText5.getText().toString();
        uservalues.username=editText.getText().toString();

        if( !editText5.getText().toString().isEmpty() && !editText6.getText().toString().isEmpty()){
            signin();
            progressDialog= ProgressDialog.show(updateappearance.this,"Appearance updating","Please Wait",false,false);

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
        rp.put("username",uservalues.username);

        client.post(Constants.Update_Appearance, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getString("res").equals("ok")) {
                        Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent i=new Intent(updateappearance.this,editprofile.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"not registered"+response+statusCode,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(updateappearance.this,statusCode + responseString,Toast.LENGTH_SHORT).show();
            }

        });

    }


}

package com.example.myapplication;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.VideoView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class careerdetails extends AppCompatActivity {

    ListView listView=null;
    ListView listView1=null;
    ListView listView2=null;
    ListView listView3=null;
    EditText editText,editText2,editText3,editText4;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careerdetails);

        ok=findViewById(R.id.careernext);

        listView=new ListView(this);
        listView1=new ListView(this);
        listView2=new ListView(this);
        listView3=new ListView(this);

        editText=(EditText) findViewById(R.id.selecteducation);
        editText2=(EditText) findViewById(R.id.selectemployeein);
        editText3=(EditText) findViewById(R.id.selectoccupation);
        editText4=(EditText) findViewById(R.id.selectincome);


        ok.setOnClickListener(new View.OnClickListener() {
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


                if(!editText.getText().toString().matches("") && !editText2.getText().toString().matches("") && !editText3.getText().toString().matches("") && !editText4.getText().toString().matches(""))
                {
                    uservalues.education=editText.getText().toString();
                    uservalues.employeein=editText2.getText().toString();
                    uservalues.occupation=editText3.getText().toString();
                    uservalues.income=editText4.getText().toString();


                            Intent i=new Intent(careerdetails.this,socialdetails.class);
                    startActivity(i);



                }
            }
        });






        final String[] item={"B.Arch","B.E/B.Tech","B.Pharma","M.E/M.Tech","M.Pharma","M.S","B.IT","BCA","MCA","B.Com","M.Com","CA","BBA","MBA","BAMS","BHMS","MBBS","M.Com","MDS","LLB" ,"PHD","High School","Diploma","Others"};
        final String[] item1={"Private Sector","Government/Public Sector","Civil Services","Defence","Business","Not Working","Other"};

        final String[] item2={"Looking for job","Not working","Retired","Student","Other","Working"};


        final String[] item3={"No Income","Rs.0-1 Lakh","Rs.1-1 Lakh","Rs.2-3 Lakh","Rs.3-4 Lakh","Rs.4-5 Lakh","Rs.5-6 Lakh","Rs.10-15 Lakh","Rs.15-20 Lakh","Rs.20-40 Lakh","Rs.40-70 Lakh","Rs.70-1 crore","Rs. 1 crore & above"};

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
           //     if(editText3.getText().toString().contains("Not working")){
                    //listView3.setVisibility(View.GONE);
                    //listView3.setFocusable(false);
                    //editText4.setEnabled(false);
                    //editText4.setVisibility(View.INVISIBLE);
                    //listView3.setOnClickListener(null);
                    //}
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

        AlertDialog.Builder builder=new AlertDialog.Builder(careerdetails.this);
        builder.setCancelable(true);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        if(!isFinishing()){
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

    public void showDialogListView2(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(careerdetails.this);
        builder.setCancelable(true);
        builder.setView(listView1);
        AlertDialog dialog=builder.create();
        if(!isFinishing()){
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView3(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(careerdetails.this);
        builder.setCancelable(true);
        builder.setView(listView2);
        AlertDialog dialog=builder.create();
        if(!isFinishing()){
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView4(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(careerdetails.this);
        builder.setCancelable(true);
        builder.setView(listView3);
        AlertDialog dialog=builder.create();
        if(!isFinishing()){
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

}


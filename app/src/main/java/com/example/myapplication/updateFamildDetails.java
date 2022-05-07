package com.example.myapplication;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class updateFamildDetails extends AppCompatActivity {


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
    EditText editText,editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12;
    Button btnfamilydetailsnext;
    String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;
    public ProgressDialog progressDialog;
    SessionManagement sessionManagement;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatefamild_details);
        sessionManagement=new SessionManagement(getApplicationContext());
        HashMap<String, String> user = sessionManagement.getUserDetails();
        id=user.get(SessionManagement.KEY_ID);
        btnfamilydetailsnext=(Button)findViewById(R.id.updatefamilydetailspage);
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


        editText=(EditText) findViewById(R.id.updatefathersoccupation);
        editText1=(EditText) findViewById(R.id.updatemothersoccupation);
        editText2=(EditText) findViewById(R.id.updatebrothers);
        editText3=(EditText) findViewById(R.id.updatebrotherofwhichmarried);
        editText4=(EditText) findViewById(R.id.updatesisters);
        editText5=(EditText) findViewById(R.id.updatesisofwhichmarried);
        editText6=(EditText) findViewById(R.id.updatefamilyincome);
        editText7=(EditText) findViewById(R.id.updatefamilystatus);
        editText8=(EditText) findViewById(R.id.updatefamilytype);
        editText9=(EditText) findViewById(R.id.updatefamilyvalue);
        editText10=(EditText) findViewById(R.id.updatetlivingwithfamily);
        editText11=(EditText) findViewById(R.id.updatefamilybasedoutof);
        editText12=(EditText) findViewById(R.id.updateaboutmyfamily);



        final String[] item={"Business/Entrepreneur","Service - Private","Service - Govt./PSU","Army/Armed Force","Civil Services","Retired","Teacher","Not Employee","Other"};

        final String[] item1={"Housewife","Business/Entrepreneur","Service - Private","Service - Govt./PSU","Army/Armed Force","Civil Services","Retired","Teacher","Not Employee","Other"};

        final String[] item2={"None","1","2","3","3+"};

        final String[] item3={"None","1","2","3","3+","All"};

        final String[] item4={"None","1","2","3","3+"};

        final String[] item5={"None","1","2","3","3+","All"};


        final String[] item6={"No Income","Rs.0-1 Lakh","Rs.1-1 Lakh","Rs.2-3 Lakh","Rs.3-4 Lakh","Rs.4-5 Lakh","Rs.5-6 Lakh","Rs.10-15 Lakh","Rs.15-20 Lakh","Rs.20-40 Lakh","Rs.40-70 Lakh","Rs.70-1 crore","Rs.1 crore & above"};

        final String[] item7={"Rich/Affluent","Upper Middle Class","Middle Class","Other"};

        final String[] item8={"Joint Family","Nuclear Family","Others"};

        final String[] item9={"Orthodox","Conservative","Moderate","Liberal","Other"};

        final String[] item10={"Not Applicable","Yes","No"};

        final String[] item11={"India","Afghanistan","Albania","Algeria","Argentina","Australia","Belgium","Brazil","Combodia","Canada","China","Colombia","Denmark","Egypt","France","Finland","Germany","Greece","Greenland","Holland","Iceland","Indonesia","Iran","Iraq","Malaysia","Myanmar","Norway","Pakistan","Philippines","Poland","Singapore","Spain","Swedan","Taiwan","Unaited Kingdom","Unaited States","Vietnam","Zambia","Others"};




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




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText.setText(txt1);
                recreate();
                a1=txt1;
                if(!a1.matches(""))
                {
                    uservalues.fatheroccupation=a1;
                }
                ((ViewGroup) view).removeView(view);

            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText1.setText(txt1);
                a2=txt1;
                recreate();
                if(!a2.matches(""))
                {
                    uservalues.motheroccupation=a2;
                }

                ((ViewGroup) view).removeView(view);
            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText2.setText(txt1);
                a3=txt1;
                recreate();
                if(!a3.matches(""))
                {
                    uservalues.brothers=a3;
                }

                ((ViewGroup) view).removeView(view);


            }
        });


        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText3.setText(txt1);
                a4=txt1;
                if(!a4.matches(""))
                {
                    uservalues.bromarried=a4;
                }
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
                editText4.setText(txt1);
                a5=txt1;
                if(!a5.matches(""))
                {
                    uservalues.sisters=a5;
                }

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
                editText5.setText(txt1);
                a6=txt1;
                recreate();
                if(!a6.matches(""))
                {
                    uservalues.sismarried=a6;
                }

                ((ViewGroup) view).removeView(view);


            }
        });


        listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg=(ViewGroup) view;
                TextView txt=(TextView) vg.findViewById(R.id.txtitems);
                String txt1=txt.getText().toString();
                editText6.setText(txt1);
                a7=txt1;
                if(!a7.matches(""))
                {
                    uservalues.familyincome=a7;
                }
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
                editText7.setText(txt1);
                a8=txt1;
                if(!a8.matches(""))
                {
                    uservalues.familystatus=a8;
                }
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
                editText8.setText(txt1);
                a9=txt1;
                if(!a9.matches(""))
                {
                    uservalues.familytype=a9;
                }
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
                editText9.setText(txt1);
                a10=txt1;
                if(!a10.matches(""))
                {
                    uservalues.familyvalue=a10;
                }
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
                editText10.setText(txt1);
                a11=txt1;
                if(!a11.matches(""))
                {
                    uservalues.livingwithfamily=a11;
                }
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
                editText11.setText(txt1);
                a12=txt1;
                if(!a12.matches(""))
                {
                    uservalues.familybasedof=a12;
                }
                recreate();

                ((ViewGroup) view).removeView(view);

            }
        });


        btnfamilydetailsnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a13=editText12.getText().toString();

                if(!a13.matches(""))
                {
                    uservalues.aboutfamily=a13;
                }
                insertdata();
            }
        });


    }

    public void showDialogListView1(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

    public void showDialogListView2(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setView(listView1);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView3(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setView(listView2);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView4(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView3);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

    public void showDialogListView5(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView4);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

    public void showDialogListView6(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView5);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView7(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView6);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView8(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView7);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }



    public void showDialogListView9(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView8);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView10(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView9);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView11(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView10);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }


    public void showDialogListView12(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(updateFamildDetails.this);
        builder.setCancelable(true);
        builder.setView(listView11);
        AlertDialog dialog=builder.create();
        if(!isFinishing()) {
            dialog.show();
        }
        else
        {
            dialog.dismiss();
        }
    }

    private void insertdata(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        SharedPreferences sharedpreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        rp.put("profileid",id);
        rp.put("aboutme",uservalues.aboutme);
        rp.put("fatheroccupation",uservalues.fatheroccupation);
        rp.put("motheroccupation",uservalues.motheroccupation);
        rp.put("brother",uservalues.brothers);
        rp.put("brothermarried",uservalues.bromarried);
        rp.put("sister",uservalues.sisters);
        rp.put("sistermarried",uservalues.sismarried);
        rp.put("aboutfamily",uservalues.aboutfamily);
        rp.put("familyincome",uservalues.familyincome);
        rp.put("familystatus",uservalues.familystatus);
        rp.put("familytype",uservalues.familytype);
        rp.put("familyvalue",uservalues.familyvalue);
        rp.put("livingwithfamily",uservalues.livingwithfamily);
        rp.put("familybasedof",uservalues.familybasedof);
        rp.put("username",uservalues.username);

        client.post(Constants.Update_family,rp,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(updateFamildDetails.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    if(response.getString("res").equals("ok")){
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(updateFamildDetails.this,editprofile.class));
                    }
                    else{
                        Toast.makeText(updateFamildDetails.this, "Wrong Creds", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(updateFamildDetails.this,editprofile.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(updateFamildDetails.this,statusCode + responseString,Toast.LENGTH_SHORT).show();
            }


        });

    }

}

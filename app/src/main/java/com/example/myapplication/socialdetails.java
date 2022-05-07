package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class socialdetails extends AppCompatActivity {

    ListView listView=null;
    ListView listView1=null;
    ListView listView2=null;
    ListView listView3=null;
    EditText editText,editText2,editText3,editText4;
    Button nextsocialdetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socialdetails);

        listView=new ListView(this);
        listView1=new ListView(this);
        listView2=new ListView(this);
        listView3=new ListView(this);




        nextsocialdetails=(Button)findViewById(R.id.btnnextsocialdetails) ;


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
                    Intent i = new Intent(socialdetails.this, reg_logindetails.class);
                    startActivity(i);
                }
                }
        });


        editText=(EditText) findViewById(R.id.selectsocial);
        editText2=(EditText) findViewById(R.id.selecttongue);
        editText3=(EditText) findViewById(R.id.selectreligion);
        editText4=(EditText) findViewById(R.id.selectcast);





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

        AlertDialog.Builder builder=new AlertDialog.Builder(socialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView2(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(socialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView1);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void showDialogListView3(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(socialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView2);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void showDialogListView4(View view){

        AlertDialog.Builder builder=new AlertDialog.Builder(socialdetails.this);
        builder.setCancelable(true);
        builder.setView(listView3);
        AlertDialog dialog=builder.create();
        dialog.show();
    }



}

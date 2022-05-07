package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityDeleteAccountBinding;

import java.util.ArrayList;

public class deleteAccount extends AppCompatActivity {
    ArrayAdapter adapter;
    ArrayList list;
    ActivityDeleteAccountBinding binding;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDeleteAccountBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        builder=new AlertDialog.Builder(this);
        getSupportActionBar().setTitle("Delete Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list=new ArrayList();
        list.add("Not Feeling Good Enough App");
        list.add("Found Better One than this");
        list.add("Not Good Feature to use");
        list.add("Not Very Efficient");
        list.add("Just Taking some Time");
        list.add("others");
        adapter=new ArrayAdapter(deleteAccount.this,android.R.layout.simple_expandable_list_item_1,list);
        binding.deleteaccountspinner.setAdapter(adapter);
        binding.deleteaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    builder.setMessage("It will Delete Your Account")
                            .setCancelable(true)
                            .setIcon(R.drawable.ic_upgrade)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Are you Sure?");
                        alert.setIcon(R.drawable.ic_question);
                         alert.show();
                }
        });
    }
}

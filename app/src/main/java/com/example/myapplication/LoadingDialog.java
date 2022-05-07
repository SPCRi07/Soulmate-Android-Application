package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Button;



class LoadingDialog {
    Activity activity;
    AlertDialog dialog;
    Button yes,no;

    LoadingDialog(Activity myactivity){
        activity=myactivity;
    }

    void startLoadingDialog(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialogbox,null));
        builder.setCancelable(true);
        builder.setTitle(message);
        dialog=builder.create();
        dialog.show();
    }

    void startLoadingDialog(String message, int r){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialogbox,null));
        builder.setCancelable(true);
        builder.setTitle(message);
        dialog=builder.create();
        builder.setIcon(r);
        dialog.show();
    }

    void startLoadingDialog(String message,String positivebuttontext){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialogbox,null));
        builder.setCancelable(true);
        builder.setTitle(message);
        builder.setPositiveButton(positivebuttontext, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismissDialog();
            }
        });
        dialog=builder.create();
        dialog.setIcon(R.drawable.ic_wifi);
        dialog.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }
}

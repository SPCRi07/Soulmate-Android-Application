package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.InputStream;

public class dashboard_cardview_dynamic extends ArrayAdapter<String> {

    private String[] Name;
    private String[] imagePath;
    private Activity context;

    Bitmap bitmap;


    public dashboard_cardview_dynamic(Activity context,String[] Name) {
        super(context,R.layout.dashboard_cardview_dynamic,Name);
        this.context=context;
        this.Name=Name;
    }


    @Nullable
    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent){
        View r=convertView;


       ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.dashboard_cardview_dynamic,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();
        }
        viewHolder.textView1.setText(Name[position]);

        viewHolder.namecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,editprofile.class);
                context.startActivity(i);
            }
        });
          return r;
    }

    class ViewHolder{
        TextView textView1;
        ImageView imageView;
        CardView namecard;

        ViewHolder(View v){

            imageView=(ImageView) v.findViewById(R.id.namephoto1);
            textView1=(TextView) v.findViewById(R.id.nametext1);
            namecard=(CardView) v.findViewById(R.id.namecard1);
            Constants.selected_user=textView1.getText().toString();

        }


    }
/*    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap>{

        ImageView imgView;
        public GetImageFromURL(ImageView imgv){
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;
            try{
                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {ex.printStackTrace();}
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){

            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }

*/

}

package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class listcardadapter extends RecyclerView.Adapter<listcardadapter.cardviewholder> implements Filterable {

    private ArrayList<listcarditems> mlistcarditems;
    private ArrayList<listcarditems> mlistcarditemsfiltered;
    public static String abc;
     SessionManagement sessionManagement;
    HashMap<String, String> user;

    Context con;
    @NonNull
    @Override
    public cardviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        cardviewholder evh;
        sessionManagement=new SessionManagement(parent.getContext());
        user = new HashMap<>();
            itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_cardview_dynamic, null, false);
            evh=new cardviewholder(itemView);
            itemView.setTag(evh);
            con=parent.getContext();
        return evh;
    }

    public listcardadapter(ArrayList<listcarditems> listcarditems){
        mlistcarditems=listcarditems;
        this.mlistcarditemsfiltered=listcarditems;
    }
    public listcardadapter(Context con1,ArrayList<listcarditems> listcarditems){
        con=con1;
        mlistcarditems=listcarditems;
        this.mlistcarditemsfiltered=listcarditems;
    }

    @Override
    public void onBindViewHolder(@NonNull cardviewholder holder, int position) {
        String imageurl;
        holder.c1.setAnimation(AnimationUtils.loadAnimation(con,R.anim.fade_transtition));
        listcarditems currentitem = mlistcarditemsfiltered.get(position);
        holder.textView1.setText(currentitem.getName());
        holder.textView2.setText(currentitem.getProfileid());
        imageurl=sessionManagement.getUserDetails().get(SessionManagement.Gender);

        assert imageurl != null;
        if(imageurl.matches("Male")) {
            Picasso.with(con).load(currentitem.getImage()).error(R.drawable.ic_girl).into(holder.imageView1);
        }
        else if(imageurl.equals("Female"))
        {
            Picasso.with(con).load(currentitem.getImage()).error(R.drawable.ic_boy).into(holder.imageView1);
        }
        else
        {
            Picasso.with(con).load(currentitem.getImage()).error(R.drawable.ic_user).into(holder.imageView1);
        }

        SharedPreferences sharedpreferences = con.getSharedPreferences("username", Context.MODE_PRIVATE);
         SharedPreferences.Editor editor=sharedpreferences.edit();
         editor.putString("username",abc);
         editor.putString("imageurl",currentitem.getImage());
         editor.apply();
    }
    @Override
    public int getItemCount() {
        return mlistcarditemsfiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key=constraint.toString();
                if(key.isEmpty()){
                    mlistcarditemsfiltered=mlistcarditems;
                }
                else
                {
                    ArrayList<listcarditems> lsfiltered=new ArrayList<>();
                    for(listcarditems row: mlistcarditems){

                        if(row.getName().contains(key)){
                            lsfiltered.add(row);
                        }
                    }
                    mlistcarditemsfiltered= lsfiltered;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mlistcarditemsfiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    mlistcarditemsfiltered= (ArrayList<listcarditems>) results.values;
                    notifyDataSetChanged();
            }
        };
    }


    public static class cardviewholder extends RecyclerView.ViewHolder  {
        TextView textView1,textView2;
        ImageView imageView1;
         CardView c1;
        private  Context context;
        SessionManagement session;
        Constants constants;

        public cardviewholder(final View itemView) {
            super(itemView);
            final String[] abc = new String[1];
            final String[] def = new String[1];
            context = itemView.getContext();
            constants=new Constants();
            session=new SessionManagement(itemView.getContext());
            textView1=(TextView) itemView.findViewById(R.id.nametext1);
            c1=(CardView)itemView.findViewById(R.id.namecard1);
            imageView1=(ImageView)itemView.findViewById(R.id.namephoto1);
            textView2=(TextView)itemView.findViewById(R.id.retrievedid);
             c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,profileview.class);
                    abc[0] =textView1.getText().toString();
                    def[0] =textView2.getText().toString();
                    i.putExtra("name1", def[0]);
                    i.putExtra("name3",abc[0]);
                    HashMap<String, String> user = session.getUserDetails();
                    String gen=user.get(SessionManagement.KEY_ID);
                    constants.addtoprofileview(def[0],gen);
                    context.startActivity(i);
                }

            });
        }
    }
}

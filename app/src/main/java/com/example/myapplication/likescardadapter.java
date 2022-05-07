package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class likescardadapter extends RecyclerView.Adapter<likescardadapter.cardviewholder> {

    private ArrayList<likescarditems> mlistcarditems;
    private String Name[];
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
        user = new HashMap<String, String>();
            itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_likes_recycleview, null, false);
            evh=new cardviewholder(itemView);
            itemView.setTag(evh);
            con=parent.getContext();
        return evh;
    }

    public likescardadapter(ArrayList<likescarditems> likescarditems){
        mlistcarditems=likescarditems;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull cardviewholder holder, int position) {
        holder.c1.setAnimation(AnimationUtils.loadAnimation(con,R.anim.fade_transtition));
        likescarditems currentitem = mlistcarditems.get(position);
                       holder.textView1.setText(currentitem.getName());
           Picasso.with(con).load(currentitem.getImage()).error(R.drawable.ic_user).into(holder.imageView1);
                        holder.textView2.setText(currentitem.getAge());
                        holder.textView3.setText(currentitem.getProfileid());
    }
    @Override
    public int getItemCount() {
        return mlistcarditems.size();
    }

    public  class cardviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1,textView2,textView3;
        CircleImageView imageView1;
        ImageButton imageButton;
        CardView c1;
        private final Context context;
        SessionManagement session;


        public cardviewholder(View itemView) {
            super(itemView);
            final String[] abc = new String[1];
            final String[] def = new String[1];
            context = itemView.getContext();
            textView1=(TextView) itemView.findViewById(R.id.likestname);
            c1=(CardView)itemView.findViewById(R.id.likesnamecard1);
            textView2=(TextView)itemView.findViewById(R.id.likesageretrieved);
            textView3=(TextView)itemView.findViewById(R.id.likesprofileidretrieved);
            imageButton=(ImageButton)itemView.findViewById(R.id.likesitemdelete);
            imageView1=(CircleImageView)itemView.findViewById(R.id.likesphoto);
            session=new SessionManagement(itemView.getContext());
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,profileview.class);
                    abc[0] =textView3.getText().toString();
                    i.putExtra("name1", abc[0]);
                    context.startActivity(i);
                }
            });


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    def[0] =textView3.getText().toString();
                    HashMap<String, String> user = session.getUserDetails();
                    String gen=user.get(SessionManagement.KEY_ID);
                    deletefromlikes(def[0],gen);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }

        public void deletefromlikes(String toprofileid, String fromprofileid){
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams rp = new RequestParams();
           rp.put("toprofileid",toprofileid);
           rp.put("fromprofileid",fromprofileid);
            client.post(Constants.LIKES_DELETE_USER,rp,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if(response.getString("res").equals("ok")) {
                            c1.setVisibility(View.GONE);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

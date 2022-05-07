package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class editprofile extends AppCompatActivity  {
    Toolbar toolbar;
    TextView txtbackprofile,txtshare;
    TextView txttakephoto;
    TextView id;
  Boolean clicked=false;
    private CircleImageView editprofile_image;
    public static final int CAMERA_REQUEST = 999;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SessionManagement sessionManagement;
    HashMap<String,String>user;
    CardView changephoto,basicdetails,careerdetails,socialdetails,appearance,partnerpreference,reg_details,familydetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        sessionManagement=new SessionManagement(getApplicationContext());
        user=sessionManagement.getUserDetails();
        id=(TextView)findViewById(R.id.backeditprofile);
        txtshare=(TextView)findViewById(R.id.share);
        String abc=user.get(SessionManagement.Profileid);
        String def=user.get(SessionManagement.Image);
       changephoto=(CardView)findViewById(R.id.card8);
        basicdetails=(CardView)findViewById(R.id.card2);
        careerdetails=(CardView)findViewById(R.id.card3);
        socialdetails=(CardView)findViewById(R.id.card4);
        appearance=(CardView)findViewById(R.id.card9);
        partnerpreference=(CardView)findViewById(R.id.card7);
        familydetails=(CardView)findViewById(R.id.card6);
        reg_details=(CardView)findViewById(R.id.card5);
        changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updateprofileupload.class);
                startActivity(i);
            }
        });
        basicdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updatepersonaldetails.class);
                startActivity(i);
            }
        });
        careerdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updatecareerdetails.class);
                startActivity(i);
            }
        });
        socialdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updatesocialdetails.class);
                startActivity(i);
            }
        });
        appearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updateappearance.class);
                startActivity(i);
            }
        });
        partnerpreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,update_partner_pref.class);
                startActivity(i);
            }
        });
        familydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updateFamildDetails.class);
                startActivity(i);
            }
        });

        reg_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(editprofile.this,updatereg_logindetails.class);
                startActivity(i);
            }
        });
        txtshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,"Hello Greetings From SoulMate");
                startActivity(Intent.createChooser(i,"Share Using"));

            }
        });

        txtbackprofile=(TextView)findViewById(R.id.backprofile);

        txtbackprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(editprofile.this,BottomNavigationActivity.class);
                startActivity(i);
            }
        });

       txttakephoto = (TextView) findViewById(R.id.takephoto);
        txttakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent();
                    intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent1, CAMERA_REQUEST);
                    clicked=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        editprofile_image = (CircleImageView) findViewById(R.id.editprofile_image);

        editprofile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                clicked=true;
                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });
        Picasso.with(getApplicationContext()).load(def).error(R.drawable.ic_user).into(editprofile_image);
        id.setText(abc);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                editprofile_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == CAMERA_REQUEST) {
            {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    editprofile_image.setImageBitmap(bitmap);
                }
            }
        }
    }


}



package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.databinding.ActivityChoosecityBinding;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.sun.mail.imap.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class choosecity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    SessionManagement session;
    ActivityChoosecityBinding binding;
    ArrayList<String>ar_state;
    ArrayAdapter arrayAdapter_city;
    ArrayList ar_gujarat,ar_andhrapradesh,ar_arunachalpradesh,ar_assam,ar_bihar,ar_chhattisgarh,ar_delhi,ar_goa,ar_haryana,ar_himachalpradesh,ar_jammukashmir,ar_jharkhand,ar_karnataka,ar_kerala,ar_madhyapradesh,ar_maharastra,ar_meghalay,ar_mizoram,ar_nagaland,ar_odisha,ar_pondichery,ar_panjab,ar_rajasthan,ar_sikkim,ar_tamilnadu,ar_tripura,ar_uttarpradesh,ar_uttarakhand,ar_westbengal,ar_country;
    LocationManager locationManager;
    Double longtitude= 0.0;
    Double latitude= 0.0;
     Location gps_loc=null,network_loc=null,final_loc=null;
    private static final String TAG_RESULT = "predictions";
    JSONObject json;


    ArrayList<String> names;
    ArrayAdapter<String> adapter;
    String browserKey = "AIzaSyCqnE-odPevLzv7o9JSsYerBnMyjyQbqg4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityChoosecityBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        names=new ArrayList<>();
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getSupportActionBar().setTitle("Choose City");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session=new SessionManagement(getApplicationContext());
        String myname=session.getUserDetails().get(SessionManagement.Name);
        String mycity=session.getUserDetails().get(SessionManagement.USER_CITY);
        String pho=session.getUserDetails().get(SessionManagement.Image);
        Picasso.with(getApplicationContext()).load(pho).error(R.drawable.ic_user).into(binding.logo1);
        String pho2=getIntent().getExtras().getString("Image");
        String name=getIntent().getExtras().getString("Name");
        String city=getIntent().getExtras().getString("City");
        Picasso.with(getApplicationContext()).load(pho2).error(R.drawable.ic_user).into(binding.logo12);
        binding.choosercity.setText(city);
        binding.chooserName.setText(name);
        binding.mycity.setText(mycity);
        binding.myName.setText(myname);
        binding.choosecitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(choosecity.this,cityfindrecycle.class));
            }
        });
        ar_state = new ArrayList<>();
        ar_state.add("Gujarat");
        ar_state.add("Andhra Pradesh");
        ar_state.add("Arunachal Pradesh");
        ar_state.add("Assam");
        ar_state.add("Bihar");
        ar_state.add("Chhattisgarh");
        ar_state.add("Delhi");
        ar_state.add("Goa");
        ar_state.add("Haryana");
        ar_state.add("Himachal Pradesh");
        ar_state.add("Jammu & Kashmir");
        ar_state.add("Jharkhand");
        ar_state.add("Karnataka");
        ar_state.add("Kerala");
        ar_state.add("Madhya Pradesh");
        ar_state.add("Maharastra");
        ar_state.add("Meghalaya");
        ar_state.add("Mizoram");
        ar_state.add("Nagaland");
        ar_state.add("Odhisha");
        ar_state.add("Pondichery");
        ar_state.add("Panjab");
        ar_state.add("Rajasthan");
        ar_state.add("Sikkim");
        ar_state.add("Tripura");
        ar_state.add("Tamil Nadu");
        ar_state.add("Uttar Pradesh");
        ar_state.add("Uttarakhand");
        ar_state.add("West Bengal");

        ar_gujarat = new ArrayList<>();
        ar_gujarat.add("Ahmedabad");
        ar_gujarat.add("Anand");
        ar_gujarat.add("Bharuch");
        ar_gujarat.add("Bhavnagar");
        ar_gujarat.add("Dahod");
        ar_gujarat.add("Dwarka");
        ar_gujarat.add("Gandhinagar");
        ar_gujarat.add("Godhra");
        ar_gujarat.add("Himmatnagar");
        ar_gujarat.add("Jamnagar");
        ar_gujarat.add("Junagath");
        ar_gujarat.add("Kalol");
        ar_gujarat.add("Khambhat");
        ar_gujarat.add("Limdi");
        ar_gujarat.add("Lunawada");
        ar_gujarat.add("Mahesana");
        ar_gujarat.add("Modasa");
        ar_gujarat.add("Morbi");
        ar_gujarat.add("Nadiad");
        ar_gujarat.add("Navsari");
        ar_gujarat.add("Ode");
        ar_gujarat.add("Okha");
        ar_gujarat.add("Palanpur");
        ar_gujarat.add("Patan");
        ar_gujarat.add("Porbandar");
        ar_gujarat.add("Rajkot");
        ar_gujarat.add("Rajpipla");
        ar_gujarat.add("Surat");
        ar_gujarat.add("Surendranagar");
        ar_gujarat.add("Valsad");
        ar_gujarat.add("Vapi");
        ar_gujarat.add("Veraval");
        ar_gujarat.add("Unjha");
        ar_gujarat.add("Una");
        ar_gujarat.add("Umreth");
        ar_gujarat.add("Nadiad");
        ar_gujarat.add("V.V.Nagar");
        ar_gujarat.add("Vadodara");
        ar_gujarat.add("Others");

        ar_andhrapradesh = new ArrayList<>();
        ar_andhrapradesh.add("Adilabad");
        ar_andhrapradesh.add("Adoni");
        ar_andhrapradesh.add("Amravati");
        ar_andhrapradesh.add("Anantpura");
        ar_andhrapradesh.add("Bhimavaram");
        ar_andhrapradesh.add("Chittoor");
        ar_andhrapradesh.add("Cuddapah");
        ar_andhrapradesh.add("Dharmavaram");
        ar_andhrapradesh.add("Eluru");
        ar_andhrapradesh.add("Guntur");
        ar_andhrapradesh.add("Hindupur");
        ar_andhrapradesh.add("Hydrabad");
        ar_andhrapradesh.add("Kadapa");
        ar_andhrapradesh.add("Kakinada");
        ar_andhrapradesh.add("Karimnagar");
        ar_andhrapradesh.add("Kurnool");
        ar_andhrapradesh.add("Machilipatnam");
        ar_andhrapradesh.add("Madamapalle");
        ar_andhrapradesh.add("Mahabubabad");
        ar_andhrapradesh.add("MAlkajgiai");
        ar_andhrapradesh.add("Nalgonda");
        ar_andhrapradesh.add("Nandyal");
        ar_andhrapradesh.add("Narasaraopet");
        ar_andhrapradesh.add("Nellore");
        ar_andhrapradesh.add("Nizamabad");
        ar_andhrapradesh.add("Ongole");
        ar_andhrapradesh.add("Palampet");
        ar_andhrapradesh.add("Suryapet");
        ar_andhrapradesh.add("Tadpatri");
        ar_andhrapradesh.add("Tenali");
        ar_andhrapradesh.add("Tirupati");
        ar_andhrapradesh.add("Vijaywada");
        ar_andhrapradesh.add("Vizag");
        ar_andhrapradesh.add("Vizianagaram");
        ar_andhrapradesh.add("Warangal");
        ar_andhrapradesh.add("Others");


        ar_arunachalpradesh = new ArrayList<>();

        ar_arunachalpradesh.add("Itanagar");
        ar_arunachalpradesh.add("Naharlagum");
        ar_arunachalpradesh.add("Pasighat");
        ar_arunachalpradesh.add("Others");


        ar_assam = new ArrayList<>();
        ar_assam.add("Amingaon");
        ar_assam.add("Barpeta");
        ar_assam.add("Bongaigaon");
        ar_assam.add("Dhubri");
        ar_assam.add("Dibrugarh");
        ar_assam.add("Diphu");
        ar_assam.add("Dispur");
        ar_assam.add("Goalpara");
        ar_assam.add("Golaghat");
        ar_assam.add("Guwahati");
        ar_assam.add("Jorhat");
        ar_assam.add("Karimaganj");
        ar_assam.add("Nagaon");
        ar_assam.add("Nawagoan");
        ar_assam.add("North Lakhimpur");
        ar_assam.add("Sibsagar");
        ar_assam.add("Silchar");
        ar_assam.add("Tezpur");
        ar_assam.add("Others");


        ar_bihar = new ArrayList<>();
        ar_bihar.add("Araria");
        ar_bihar.add("Arrah");
        ar_bihar.add("Aurangabad");
        ar_bihar.add("Bagaha");
        ar_bihar.add("Banka");
        ar_bihar.add("Begusarai");
        ar_bihar.add("Bettiah");
        ar_bihar.add("Bhabua");
        ar_bihar.add("Bhagalpur");
        ar_bihar.add("Bihar Sharif");
        ar_bihar.add("Buxar");
        ar_bihar.add("Chapra");
        ar_bihar.add("Darbhanga");
        ar_bihar.add("Dehri");
        ar_bihar.add("Dinapur Nizamat");
        ar_bihar.add("Gaya");
        ar_bihar.add("Gopalganj");
        ar_bihar.add("Hajipur");
        ar_bihar.add("Jamalpur");
        ar_bihar.add("Jamui");
        ar_bihar.add("Jehanabad");
        ar_bihar.add("Kaimur");
        ar_bihar.add("Katihar");
        ar_bihar.add("Kashanganj");
        ar_bihar.add("Lakhisasai");
        ar_bihar.add("Madhepura");
        ar_bihar.add("Madhubani");
        ar_bihar.add("Motihari");
        ar_bihar.add("Munger");
        ar_bihar.add("Muzaffarpur");
        ar_bihar.add("Nawada");
        ar_bihar.add("Patna");
        ar_bihar.add("Purnia");
        ar_bihar.add("Ramnagar");
        ar_bihar.add("Saharsa");
        ar_bihar.add("Samastipur");
        ar_bihar.add("Sasaram");
        ar_bihar.add("Sitamarhi");
        ar_bihar.add("Siwan");
        ar_bihar.add("Supaul");
        ar_bihar.add("Others");


        ar_chhattisgarh = new ArrayList<>();
        ar_chhattisgarh.add("Ambikapur");
        ar_chhattisgarh.add("Bemetara");
        ar_chhattisgarh.add("Bhatapara");
        ar_chhattisgarh.add("Bhilai");
        ar_chhattisgarh.add("Bikapur");
        ar_chhattisgarh.add("Bilaspur");
        ar_chhattisgarh.add("Dalli-Rajhara");
        ar_chhattisgarh.add("Dhamtrari");
        ar_chhattisgarh.add("Durg");
        ar_chhattisgarh.add("Korba");
        ar_chhattisgarh.add("Jagdalpur");
        ar_chhattisgarh.add("Mahasamund");
        ar_chhattisgarh.add("Naila Jangir");
        ar_chhattisgarh.add("Patan");
        ar_chhattisgarh.add("Raigarh");
        ar_chhattisgarh.add("Raipur");
        ar_chhattisgarh.add("Rajnandgaon");
        ar_chhattisgarh.add("Others");


        ar_delhi = new ArrayList<>();
        ar_delhi.add("New Delhi");
        ar_delhi.add("Others");


        ar_goa = new ArrayList<>();
        ar_goa.add("Mapusa");
        ar_goa.add("Margaon");
        ar_goa.add("Panaji");
        ar_goa.add("Others");


        ar_haryana = new ArrayList<>();
        ar_haryana.add("Ambala");
        ar_haryana.add("Bahadurgarh");
        ar_haryana.add("Faridabad");
        ar_haryana.add("Fatehabad");
        ar_haryana.add("Gurgaon");
        ar_haryana.add("Hansi");
        ar_haryana.add("Hissar");
        ar_haryana.add("jagadhari");
        ar_haryana.add("Jhajjar");
        ar_haryana.add("Kaithal");
        ar_haryana.add("Karnal");
        ar_haryana.add("Kurukshetra");
        ar_haryana.add("Mahendragarh");
        ar_haryana.add("Narnaual");
        ar_haryana.add("Nuh");
        ar_haryana.add("Palwal");
        ar_haryana.add("Panipat");
        ar_haryana.add("Rewari");
        ar_haryana.add("Rohtak");
        ar_haryana.add("Sirsa");
        ar_haryana.add("Thanesar");
        ar_haryana.add("Others");


        ar_himachalpradesh = new ArrayList<>();
        ar_himachalpradesh.add("Baddi");
        ar_himachalpradesh.add("Chamba");
        ar_himachalpradesh.add("Dalhousie");
        ar_himachalpradesh.add("Kangra");
        ar_himachalpradesh.add("Kullu");
        ar_himachalpradesh.add("Mandi");
        ar_himachalpradesh.add("Nahan");
        ar_himachalpradesh.add("Ponta Sahib");
        ar_himachalpradesh.add("Shimla");
        ar_himachalpradesh.add("Solan");
        ar_himachalpradesh.add("Una");
        ar_himachalpradesh.add("Others");


        ar_jammukashmir = new ArrayList<>();
        ar_jammukashmir.add("Anantnag");
        ar_jammukashmir.add("Baramula");
        ar_jammukashmir.add("Gulmarg");
        ar_jammukashmir.add("Jammu");
        ar_jammukashmir.add("Leh");
        ar_jammukashmir.add("Srinagar");
        ar_jammukashmir.add("Udhampur");
        ar_jammukashmir.add("Others");


        ar_jharkhand = new ArrayList<>();
        ar_jharkhand.add("Adityapur");
        ar_jharkhand.add("Bokaro");
        ar_jharkhand.add("Chaibasa");
        ar_jharkhand.add("Chass");
        ar_jharkhand.add("Chatra");
        ar_jharkhand.add("Daltonganj");
        ar_jharkhand.add("Deoghar");
        ar_jharkhand.add("Dumka");
        ar_jharkhand.add("Garhwa");
        ar_jharkhand.add("Godda");
        ar_jharkhand.add("Gumla");
        ar_jharkhand.add("Hazaribag");
        ar_jharkhand.add("Jamtara");
        ar_jharkhand.add("Jharia");
        ar_jharkhand.add("Mango");
        ar_jharkhand.add("Ranchi");
        ar_jharkhand.add("Sahibganj");
        ar_jharkhand.add("Seraikela");
        ar_jharkhand.add("Sindri");
        ar_jharkhand.add("Others");


        ar_karnataka = new ArrayList<>();
        ar_karnataka.add("Ankola");
        ar_karnataka.add("Bagalkot");
        ar_karnataka.add("Bangalor");
        ar_karnataka.add("Bellary");
        ar_karnataka.add("Bjadarvati");
        ar_karnataka.add("Bellary");
        ar_karnataka.add("Bidar");
        ar_karnataka.add("Bijapur");
        ar_karnataka.add("Chitradurgha");
        ar_karnataka.add("Davangere");
        ar_karnataka.add("Dharwad");
        ar_karnataka.add("Gulbarga");
        ar_karnataka.add("Hassan");
        ar_karnataka.add("Haveli");
        ar_karnataka.add("Hospet");
        ar_karnataka.add("Hubli");
        ar_karnataka.add("Jamkhandi");
        ar_karnataka.add("Koppal");
        ar_karnataka.add("Madikeri");
        ar_karnataka.add("Mandya");
        ar_karnataka.add("Mysore");
        ar_karnataka.add("Raichur");
        ar_karnataka.add("Ramanagara");
        ar_karnataka.add("Udupi");
        ar_karnataka.add("Vijayapura");
        ar_karnataka.add("Tumkur");
        ar_karnataka.add("Yadgir");
        ar_karnataka.add("Others");


        ar_kerala = new ArrayList<>();
        ar_kerala.add("Alappuzha");
        ar_kerala.add("Alleppey");
        ar_kerala.add("Cannanore");
        ar_kerala.add("Cherthala");
        ar_kerala.add("Cochin");
        ar_kerala.add("Edathala");
        ar_kerala.add("Kakkanad");
        ar_kerala.add("Kannur");
        ar_kerala.add("Kollam");
        ar_kerala.add("Kottayam");
        ar_kerala.add("Kovalam");
        ar_kerala.add("Malappuram");
        ar_kerala.add("Manjeri");
        ar_kerala.add("Painavu");
        ar_kerala.add("Palghat");
        ar_kerala.add("Ponnani");
        ar_kerala.add("Quilandy");
        ar_kerala.add("Quilon");
        ar_kerala.add("Taliparamba");
        ar_kerala.add("Thrissur");
        ar_kerala.add("Vadakara");
        ar_kerala.add("Others");

        ar_madhyapradesh = new ArrayList<>();
        ar_madhyapradesh.add("Bandhavgarh");
        ar_madhyapradesh.add("Bandhavgarh");
        ar_madhyapradesh.add("Gwalior");
        ar_madhyapradesh.add("Kanha");
        ar_madhyapradesh.add("Datia");
        ar_madhyapradesh.add("Deora");
        ar_madhyapradesh.add("Dewas");
        ar_madhyapradesh.add("Dhar");
        ar_madhyapradesh.add("Guna");
        ar_madhyapradesh.add("Indore");
        ar_madhyapradesh.add("Jabalpur");
        ar_madhyapradesh.add("Katni");
        ar_madhyapradesh.add("Khandwa");
        ar_madhyapradesh.add("Mandla");
        ar_madhyapradesh.add("Mhow");
        ar_madhyapradesh.add("Morena");
        ar_madhyapradesh.add("Murwara");
        ar_madhyapradesh.add("Nagda");
        ar_madhyapradesh.add("Neemuch");
        ar_madhyapradesh.add("Panna");
        ar_madhyapradesh.add("Pithampur");
        ar_madhyapradesh.add("Raisen");
        ar_madhyapradesh.add("Rajgarh");
        ar_madhyapradesh.add("Ratangarh");
        ar_madhyapradesh.add("Rewa");
        ar_madhyapradesh.add("Sagar");
        ar_madhyapradesh.add("Santa");
        ar_madhyapradesh.add("Sidhi");
        ar_madhyapradesh.add("Tikamgarh");
        ar_madhyapradesh.add("Ujjain");
        ar_madhyapradesh.add("Vidisha");
        ar_madhyapradesh.add("Waidham");
        ar_madhyapradesh.add("Others");


        ar_maharastra = new ArrayList<>();
        ar_maharastra.add("Amravati");
        ar_maharastra.add("Aurangabad");
        ar_maharastra.add("Akola");
        ar_maharastra.add("Alibag");
        ar_maharastra.add("Badlapur");
        ar_maharastra.add("Barish");
        ar_maharastra.add("Beed");
        ar_maharastra.add("Bhandara");
        ar_maharastra.add("Chandrapur");
        ar_maharastra.add("Dhule");
        ar_maharastra.add("Gadchiroli");
        ar_maharastra.add("Hinagaghat");
        ar_maharastra.add("Jalgaon");
        ar_maharastra.add("Khandala");
        ar_maharastra.add("Kolhlapur");
        ar_maharastra.add("Latur");
        ar_maharastra.add("Mumbai");
        ar_maharastra.add("Nagpur");
        ar_maharastra.add("Nashik");
        ar_maharastra.add("Navi Mumbai");
        ar_maharastra.add("Osmanabad");
        ar_maharastra.add("Panvel");
        ar_maharastra.add("Ratnagiri");
        ar_maharastra.add("Solapur");
        ar_maharastra.add("Shiridi");
        ar_maharastra.add("Satara");
        ar_maharastra.add("Udgir");
        ar_maharastra.add("Vidarbha");
        ar_maharastra.add("Wardha");
        ar_maharastra.add("Yavatmal");
        ar_maharastra.add("Others");


        ar_meghalay = new ArrayList<>();
        ar_meghalay.add("Jawai");
        ar_meghalay.add("Mawkyrwat");
        ar_meghalay.add("Mawlai");
        ar_meghalay.add("Nongpoh");
        ar_meghalay.add("Shillong");
        ar_meghalay.add("Tura");
        ar_meghalay.add("Others");

        ar_mizoram = new ArrayList<>();
        ar_mizoram.add("Aizawal");
        ar_mizoram.add("Champhai");
        ar_mizoram.add("Lungleli");
        ar_mizoram.add("Others");


        ar_nagaland = new ArrayList<>();
        ar_nagaland.add("Dimapur");
        ar_nagaland.add("Kohima");
        ar_nagaland.add("Mokokchung");
        ar_nagaland.add("Tuenasang");
        ar_nagaland.add("Wokha");
        ar_nagaland.add("Zunheboto");
        ar_nagaland.add("Others");


        ar_odisha = new ArrayList<>();
        ar_odisha.add("Angul");
        ar_odisha.add("Balangir");
        ar_odisha.add("Baleshwar Town");
        ar_odisha.add("Barbil");
        ar_odisha.add("Bhadrak");
        ar_odisha.add("Chttack");
        ar_odisha.add("Jatni");
        ar_odisha.add("Jeypur");
        ar_odisha.add("Khordha");
        ar_odisha.add("Korpur");
        ar_odisha.add("Panikoili");
        ar_odisha.add("Puri");
        ar_odisha.add("Rajagangpur");
        ar_odisha.add("Rayagada");
        ar_odisha.add("Rourkela");
        ar_odisha.add("Sambalapur");
        ar_odisha.add("Sumbalapur");
        ar_odisha.add("Others");

        ar_pondichery = new ArrayList<>();
        ar_pondichery.add("Ozhukarai");
        ar_pondichery.add("Puducherry");
        ar_pondichery.add("Others");


        ar_panjab = new ArrayList<>();
        ar_panjab.add("Abohar");
        ar_panjab.add("Amritsar");
        ar_panjab.add("Batala");
        ar_panjab.add("Dhuri");
        ar_panjab.add("Faridkot");
        ar_panjab.add("Fazilka");
        ar_panjab.add("Gurdaspur");
        ar_panjab.add("Hoshiarpur");
        ar_panjab.add("Jagraon");
        ar_panjab.add("Jalandhar");
        ar_panjab.add("Khanna");
        ar_panjab.add("Ludhiana");
        ar_panjab.add("Malerkotrla");
        ar_panjab.add("Malout");
        ar_panjab.add("Moga");
        ar_panjab.add("Mohali");
        ar_panjab.add("Nabha");
        ar_panjab.add("Patiala");
        ar_panjab.add("Rajpura");
        ar_panjab.add("Samana");
        ar_panjab.add("Sangrur");
        ar_panjab.add("Sunam");
        ar_panjab.add("Others");


        ar_rajasthan = new ArrayList<>();
        ar_rajasthan.add("Ajmer");
        ar_rajasthan.add("Alwar");
        ar_rajasthan.add("Balotra");
        ar_rajasthan.add("Banswara");
        ar_rajasthan.add("Baran");
        ar_rajasthan.add("Bikaner");
        ar_rajasthan.add("Bundi");
        ar_rajasthan.add("Churu");
        ar_rajasthan.add("Dholpur");
        ar_rajasthan.add("Hindaum");
        ar_rajasthan.add("Jaipur");
        ar_rajasthan.add("Jothpur");
        ar_rajasthan.add("Kota");
        ar_rajasthan.add("Pali");
        ar_rajasthan.add("Sikar");
        ar_rajasthan.add("Siroli");
        ar_rajasthan.add("Sujangarh");
        ar_rajasthan.add("Sardarshahar");
        ar_rajasthan.add("Tonk");
        ar_rajasthan.add("Udaipur");
        ar_rajasthan.add("Kishangarh");
        ar_rajasthan.add("Others");


        ar_sikkim = new ArrayList<>();
        ar_sikkim.add("Gangtok");
        ar_sikkim.add("Others");


        ar_tamilnadu = new ArrayList<>();
        ar_tamilnadu.add("Alandurai");
        ar_tamilnadu.add("Ambattur");
        ar_tamilnadu.add("Ambur");
        ar_tamilnadu.add("Arcot");
        ar_tamilnadu.add("Avadi");
        ar_tamilnadu.add("Channai");
        ar_tamilnadu.add("Coimbatore");
        ar_tamilnadu.add("Dharmapuri");
        ar_tamilnadu.add("Erode");
        ar_tamilnadu.add("Hosur");
        ar_tamilnadu.add("Kalpakkam");
        ar_tamilnadu.add("Kanchipuram");
        ar_tamilnadu.add("Krishnagiri");
        ar_tamilnadu.add("Madurai");
        ar_tamilnadu.add("Mahabalipuram");
        ar_tamilnadu.add("Namakkal");
        ar_tamilnadu.add("Nilgiri");
        ar_tamilnadu.add("Ooty");
        ar_tamilnadu.add("Pallavaram");
        ar_tamilnadu.add("Periyar");
        ar_tamilnadu.add("Rajapalayam");
        ar_tamilnadu.add("Ramanathapuram");
        ar_tamilnadu.add("Salem");
        ar_tamilnadu.add("Sivaganga");
        ar_tamilnadu.add("Sivakasi");
        ar_tamilnadu.add("Tambaram");
        ar_tamilnadu.add("Theni");
        ar_tamilnadu.add("Thoothukudi");
        ar_tamilnadu.add("Triopuppur");
        ar_tamilnadu.add("Tiruvallur");
        ar_tamilnadu.add("Tiruppur");
        ar_tamilnadu.add("Tiruvannamalai");
        ar_tamilnadu.add("Tuticoirin");
        ar_tamilnadu.add("Valparai");
        ar_tamilnadu.add("Others");


        ar_tripura = new ArrayList<>();
        ar_tripura.add("Agartala");
        ar_tripura.add("Others");


        ar_uttarpradesh = new ArrayList<>();
        ar_uttarpradesh.add("Agra");
        ar_uttarpradesh.add("Aligarh");
        ar_uttarpradesh.add("Ayodhya");
        ar_uttarpradesh.add("Allahabad");
        ar_uttarpradesh.add("Amethi");
        ar_uttarpradesh.add("Amroha");
        ar_uttarpradesh.add("Auraiya");
        ar_uttarpradesh.add("Ayodhya");
        ar_uttarpradesh.add("Asamgarh");
        ar_uttarpradesh.add("Baghpat");
        ar_uttarpradesh.add("Bahraich");
        ar_uttarpradesh.add("Ballia");
        ar_uttarpradesh.add("Balrampur");
        ar_uttarpradesh.add("Baraut");
        ar_uttarpradesh.add("Basti");
        ar_uttarpradesh.add("Budam");
        ar_uttarpradesh.add("Chandauli");
        ar_uttarpradesh.add("Dearia");
        ar_uttarpradesh.add("Etah");
        ar_uttarpradesh.add("Faizabad");
        ar_uttarpradesh.add("Fatehgarh");
        ar_uttarpradesh.add("Farrukhadab");
        ar_uttarpradesh.add("Gonda");
        ar_uttarpradesh.add("Gyanpur");
        ar_uttarpradesh.add("Hapur");
        ar_uttarpradesh.add("Hardoi");
        ar_uttarpradesh.add("Hathras");
        ar_uttarpradesh.add("Jhansi");
        ar_uttarpradesh.add("Kanpur");
        ar_uttarpradesh.add("Kasganj");
        ar_uttarpradesh.add("Khora");
        ar_uttarpradesh.add("Khurja");
        ar_uttarpradesh.add("Lacknow");
        ar_uttarpradesh.add("Mainpuri");
        ar_uttarpradesh.add("Mathura");
        ar_uttarpradesh.add("Meerut");
        ar_uttarpradesh.add("Mirzapur");
        ar_uttarpradesh.add("Modi Nagar");
        ar_uttarpradesh.add("Noida");
        ar_uttarpradesh.add("Orai");
        ar_uttarpradesh.add("Padrauna");
        ar_uttarpradesh.add("Pilibhit");
        ar_uttarpradesh.add("Rae Bareli");
        ar_uttarpradesh.add("Rampur");
        ar_uttarpradesh.add("Rudrapur");
        ar_uttarpradesh.add("Sahibabad");
        ar_uttarpradesh.add("Sambhal");
        ar_uttarpradesh.add("Shamli");
        ar_uttarpradesh.add("Shikohabad");
        ar_uttarpradesh.add("Sitapur");
        ar_uttarpradesh.add("Sultanpur");
        ar_uttarpradesh.add("Unnao");
        ar_uttarpradesh.add("Varanasi");
        ar_uttarpradesh.add("Others");


        ar_uttarakhand = new ArrayList<>();
        ar_uttarakhand.add("Almora");
        ar_uttarakhand.add("Dehradun");
        ar_uttarakhand.add("Haldwani");
        ar_uttarakhand.add("Haridwar");
        ar_uttarakhand.add("Kashipur");
        ar_uttarakhand.add("Kicha");
        ar_uttarakhand.add("Mussoorie");
        ar_uttarakhand.add("Naintal");
        ar_uttarakhand.add("Pithoragarh");
        ar_uttarakhand.add("Rishikesh");
        ar_uttarakhand.add("Roorkee");
        ar_uttarakhand.add("Rudrapur");
        ar_uttarakhand.add("Tehri");
        ar_uttarakhand.add("Udham Singh Nagar");
        ar_uttarakhand.add("Others");


        ar_westbengal = new ArrayList<>();
        ar_westbengal.add("Alipore");
        ar_westbengal.add("Alipurduar");
        ar_westbengal.add("Asansol");
        ar_westbengal.add("Baharampur");
        ar_westbengal.add("Baidyabati");
        ar_westbengal.add("Bally");
        ar_westbengal.add("Balurghat");
        ar_westbengal.add("Bankura");
        ar_westbengal.add("Bansberia");
        ar_westbengal.add("Baranagar");
        ar_westbengal.add("Barasat");
        ar_westbengal.add("Basirhat");
        ar_westbengal.add("Burnpur");
        ar_westbengal.add("Champdani");
        ar_westbengal.add("Coochbehar");
        ar_westbengal.add("Dum Dum");
        ar_westbengal.add("Habra");
        ar_westbengal.add("Haldia");
        ar_westbengal.add("Halisahar");
        ar_westbengal.add("Jamurai");
        ar_westbengal.add("Kalyani");
        ar_westbengal.add("Kamarhati");
        ar_westbengal.add("Khardaha");
        ar_westbengal.add("Kulti");
        ar_westbengal.add("Malda");
        ar_westbengal.add("Midnapore");
        ar_westbengal.add("Nabadwip");
        ar_westbengal.add("Naihati");
        ar_westbengal.add("Panihti");
        ar_westbengal.add("Puruliya");
        ar_westbengal.add("Rishra");
        ar_westbengal.add("Raniganj");
        ar_westbengal.add("Santipura");
        ar_westbengal.add("Siliguri");
        ar_westbengal.add("Serampore");
        ar_westbengal.add("Suri");
        ar_westbengal.add("Tamluk");
        ar_westbengal.add("Titagarh");
        ar_westbengal.add("Uluberia");
        ar_westbengal.add("Others");


        binding.statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_gujarat);
                }

                if (position == 1) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_andhrapradesh);
                }

                if (position == 2) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_arunachalpradesh);
                }

                if (position == 3) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_assam);
                }

                if (position == 4) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_bihar);
                }

                if (position == 5) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_chhattisgarh);
                }

                if (position == 6) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_delhi);
                }

                if (position == 7) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_goa);
                }


                if (position == 8) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_haryana);
                }


                if (position == 9) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_himachalpradesh);
                }


                if (position == 10) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_jammukashmir);
                }


                if (position == 11) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_jharkhand);
                }


                if (position == 12) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_karnataka);
                }


                if (position == 13) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_kerala);
                }


                if (position == 14) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_madhyapradesh);
                }


                if (position == 15) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_maharastra);
                }


                if (position == 16) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_meghalay);
                }


                if (position == 17) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_mizoram);
                }


                if (position == 18) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_nagaland);
                }


                if (position == 19) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_odisha);
                }


                if (position == 20) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_pondichery);
                }


                if (position == 21) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_panjab);
                }


                if (position == 22) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_rajasthan);
                }


                if (position == 23) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_sikkim);
                }


                if (position == 24) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_tripura);
                }


                if (position == 25) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_uttarpradesh);
                }


                if (position == 26) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_uttarakhand);
                }


                if (position == 27) {
                    arrayAdapter_city = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_westbengal);
                }
                binding.cityspinner.setAdapter(arrayAdapter_city);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final ArrayAdapter ad1 = new ArrayAdapter(choosecity.this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, ar_state);
        binding.statespinner.setAdapter(ad1);


        binding.getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           binding.locationget.setText(herelocation());
            }
        });

        binding.locationget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=binding.locationget.getText().toString();
                try {
                    match(s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void getTheUserPermission() {
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_LOCATION);
    }
    public void OnGPS() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choosecity.this.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public String herelocation(){
                String Tag="abc";
        if(ActivityCompat.checkSelfPermission(choosecity.this,Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(choosecity.this,Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(choosecity.this,Manifest.permission.ACCESS_NETWORK_STATE) !=PackageManager.PERMISSION_GRANTED )  {
            getTheUserPermission();
        }
        else if((!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))){
            OnGPS();
        }

        else
        {
            try {
                gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if(gps_loc!=null){
                final_loc=gps_loc;
                longtitude=final_loc.getLongitude();
                latitude=final_loc.getLatitude();
            }
            else  if(network_loc!=null){
                final_loc=network_loc;
                longtitude=final_loc.getLongitude();
                latitude=final_loc.getLatitude();
            }
            else{
                latitude=0.0;
                longtitude=0.0;
            }

            try {
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses=geocoder.getFromLocation(latitude,longtitude,1);

                    if(addresses.size()>0){
                        for(Address adr:addresses){
                            if(adr.getLocality() != null && adr.getLocality().length()>0){
                                String def=adr.getLocality();
                                Tag=def;
                                updateList(Tag);
                            }
                            else
                            {
                                String def=adr.getAdminArea();
                                Tag=def;
                            }
                        }
                    }
                    return Tag;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                e.printStackTrace();}
        }
        return Tag;
    }

    public void updateList(String place) {
        String input = "";

        try {
            input = "input=" + URLEncoder.encode(place, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        String output = "json";
        String parameter = input + "&types=geocode&sensor=true&key="                + browserKey;

       String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"                + output + "?" + parameter;


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("response"+response);
                try {
                    JSONArray ja = response.getJSONArray(TAG_RESULT);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject c = ja.getJSONObject(i);
                        String description = c.getString("description");
                        System.out.println("description"+description);
                        names.add(description);
                    }

                    adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1, names);
                    adapter.notifyDataSetChanged();
                    if(adapter.isEmpty()){
                        Toast.makeText(getApplicationContext(),"not received",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String s=names.get(0);
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ignored) {
                }
            }
        },
                new Response.ErrorListener() {
            @Override            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    private void match(String location) throws UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        String input = "";
        input = "input=" + URLEncoder.encode(location, "utf-8");
        String parameter = "location=-33.8670,151.1957&radius=500&types=food&name=cruise&key="                + browserKey;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/"                + output + "?" + parameter;
        client.post(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    if(response.getString("status").equals("OK")) {

                        JSONArray ja = response.getJSONArray("results");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject c = ja.getJSONObject(i);
                            String description = c.getString("name");
                            names.add(description);
                        }
                        if(names.size()!=0){
                            Toast.makeText(getApplicationContext(),names.get(0),Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getApplicationContext(),statusCode + responseString,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

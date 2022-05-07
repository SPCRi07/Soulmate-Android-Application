package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SearchDashboardFragment extends Fragment {

    Spinner sp,sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9,sp10,sp11,sp12;
    ArrayList<String> ar,ar1,ar2,ar3,ar4,ar5,ar6,ar7,ar8,ar9,ar10,ar11,ar12;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_search_dashboard,container,false);


        sp=(Spinner)rootview.findViewById(R.id.spinnerminage);
        ar=new ArrayList<>();
        ar.add("Doesn't Matter");
        ar.add("18 years");
        ar.add("19 years");
        ar.add("20 years");
        ar.add("21 years");
        ar.add("22 years");
        ar.add("23 years");
        ar.add("24 years");
        ar.add("25 years");
        ar.add("26 years");
        ar.add("27 years");
        ar.add("28 years");
        ar.add("29 years");
        ar.add("30 years");
        ar.add("31 years");
        ar.add("32 years");
        ar.add("33 years");
        ar.add("34 years");
        ar.add("35 years");
        ar.add("36 years");
        ar.add("37 years");
        ar.add("38 years");
        ar.add("39 years");
        ar.add("40 years");
        ar.add("above 40 years");


        sp1=(Spinner)rootview.findViewById(R.id.spinnermaxage);
        ar1=new ArrayList<>();
        ar1.add("Doesn't Matter");
        ar1.add("18 years");
        ar1.add("19 years");
        ar1.add("20 years");
        ar1.add("21 years");
        ar1.add("22 years");
        ar1.add("23 years");
        ar1.add("24 years");
        ar1.add("25 years");
        ar1.add("26 years");
        ar1.add("27 years");
        ar1.add("28 years");
        ar1.add("29 years");
        ar1.add("30 years");
        ar1.add("31 years");
        ar1.add("32 years");
        ar1.add("33 years");
        ar1.add("34 years");
        ar1.add("35 years");
        ar1.add("36 years");
        ar1.add("37 years");
        ar1.add("38 years");
        ar1.add("39 years");
        ar1.add("40 years");
        ar1.add("41 years");
        ar1.add("42 years");
        ar1.add("43 years");
        ar1.add("44 years");
        ar1.add("45 years");
        ar1.add("46 years");
        ar1.add("47 years");
        ar1.add("48 years");
        ar1.add("49 years");
        ar1.add("50 years");
        ar1.add("51 years");
        ar1.add("52 years");
        ar1.add("53 years");
        ar1.add("54 years");
        ar1.add("55 years");
        ar1.add("56 years");
        ar1.add("57 years");
        ar1.add("58 years");
        ar1.add("59 years");
        ar1.add("60 years");
        ar1.add("61 years");
        ar1.add("62 years");
        ar1.add("63 years");
        ar1.add("64 years");
        ar1.add("65 years");
        ar1.add("66 years");
        ar1.add("67 years");
        ar1.add("68 years");
        ar1.add("69 years");
        ar1.add("70 years");
        ar1.add("above 70 years");


        sp2=(Spinner)rootview.findViewById(R.id.spinnerminheight);
        ar2=new ArrayList<>();
        ar2.add("Doesn't Matter");
        ar2.add("4'0 feet");
        ar2.add("4'1 feet");
        ar2.add("4'2 feet");
        ar2.add("4'3 feet");
        ar2.add("4'4 feet");
        ar2.add("4'5 feet");
        ar2.add("4'6 feet");
        ar2.add("4'7 feet");
        ar2.add("4'8 feet");
        ar2.add("4'9 feet");
        ar2.add("5'0 feet");
        ar2.add("5'1 feet");
        ar2.add("5'2 feet");
        ar2.add("5'3 feet");
        ar2.add("5'4 feet");
        ar2.add("5'5 feet");
        ar2.add("5'6 feet");
        ar2.add("5'7 feet");
        ar2.add("5'8 feet");
        ar2.add("5'9 feet");
        ar2.add("6'1 feet");
        ar2.add("6'2 feet");
        ar2.add("6'3 feet");
        ar2.add("6'4 feet");
        ar2.add("6'5 feet");
        ar2.add("6'6 feet");
        ar2.add("6'7 feet");
        ar2.add("6'8 feet");
        ar2.add("6'9 feet");
        ar2.add("7'0 feet");


        sp3=(Spinner)rootview.findViewById(R.id.spinnermaxheight);
        ar3=new ArrayList<>();
        ar3.add("Doesn't Matter");
        ar3.add("4'0 feet");
        ar3.add("4'1 feet");
        ar3.add("4'2 feet");
        ar3.add("4'3 feet");
        ar3.add("4'4 feet");
        ar3.add("4'5 feet");
        ar3.add("4'6 feet");
        ar3.add("4'7 feet");
        ar3.add("4'8 feet");
        ar3.add("4'9 feet");
        ar3.add("5'0 feet");
        ar3.add("5'1 feet");
        ar3.add("5'2 feet");
        ar3.add("5'3 feet");
        ar3.add("5'4 feet");
        ar3.add("5'5 feet");
        ar3.add("5'6 feet");
        ar3.add("5'7 feet");
        ar3.add("5'8 feet");
        ar3.add("5'9 feet");
        ar3.add("6'1 feet");
        ar3.add("6'2 feet");
        ar3.add("6'3 feet");
        ar3.add("6'4 feet");
        ar3.add("6'5 feet");
        ar3.add("6'6 feet");
        ar3.add("6'7 feet");
        ar3.add("6'8 feet");
        ar3.add("6'9 feet");
        ar3.add("7'0 feet");



        sp4=(Spinner)rootview.findViewById(R.id.spinnerstate);
        ar4=new ArrayList<>();

        ar4.add("Doesn't Matter");
        ar4.add("Andhra Pradesh");
        ar4.add("Arunachal Pradesh");
        ar4.add("Assam");
        ar4.add("Bihar");
        ar4.add("Chhattisgarh");
        ar4.add("Daman & Diu");
        ar4.add("Delhi");
        ar4.add("Goa");
        ar4.add("Gujarat");
        ar4.add("Haryana");
        ar4.add("Himachal Pradesh");
        ar4.add("Jammu & Kashmir");
        ar4.add("Jharkhand");
        ar4.add("Karnataka");
        ar4.add("kerala");
        ar4.add("Madhya Pradesh");
        ar4.add("Maharastra");
        ar4.add("Manipur");
        ar4.add("Meghalay");
        ar4.add("Panjab");
        ar4.add("Rajasthan");
        ar4.add("Sikkim");
        ar4.add("Tamil Nadu");
        ar4.add("Uttar Pradesh");
        ar4.add("Uttarakhand");



        sp5=(Spinner)rootview.findViewById(R.id.spinnercity);
        ar5=new ArrayList<>();

        ar5.add("Doesn't Matter");
        ar5.add("Ahmedabad");
        ar5.add("Anand");
        ar5.add("Bharuch");
        ar5.add("Bhavnagar");
        ar5.add("Dahod");
        ar5.add("Dwarka");
        ar5.add("Gandhinagar");
        ar5.add("Godhra");
        ar5.add("Himmatnagar");
        ar5.add("Jamnagar");
        ar5.add("Junagath");
        ar5.add("Kalol");
        ar5.add("Khambhat");
        ar5.add("Limdi");
        ar5.add("Lunawada");
        ar5.add("Mahesana");
        ar5.add("Modasa");
        ar5.add("Morbi");
        ar5.add("Nadiad");
        ar5.add("Navsari");
        ar5.add("Ode");
        ar5.add("Okha");
        ar5.add("Palanpur");
        ar5.add("Patan");
        ar5.add("Porbandar");
        ar5.add("Rajkot");
        ar5.add("Rajpipla");
        ar5.add("Surat");
        ar5.add("Surendranagar");
        ar5.add("Valsad");
        ar5.add("Vapi");
        ar5.add("Veraval");
        ar5.add("Unjha");
        ar5.add("Una");
        ar5.add("Umreth");
        ar5.add("Others");




        sp6=(Spinner)rootview.findViewById(R.id.spinnerriligion);
        ar6=new ArrayList<>();
        ar6.add("Doesn't Matter");
        ar6.add("Hindu");
        ar6.add("Muslim");
        ar6.add("Sikh");
        ar6.add("Christian");
        ar6.add("Buddhist");
        ar6.add("Jain");
        ar6.add("Parsi");


        sp7=(Spinner)rootview.findViewById(R.id.spinnercaste);
        ar7=new ArrayList<>();
        ar7.add("Doesn't Matter");
        ar7.add("Agri");
        ar7.add("Bhavsar");
        ar7.add("Dhobi");
        ar7.add("Gatti");
        ar7.add("Gopan");
        ar7.add("Arora");
        ar7.add("Jat");
        ar7.add("Kalal");
        ar7.add("Kapu");
        ar7.add("Kohli");
        ar7.add("Maru");
        ar7.add("Nair");
        ar7.add("Nepali");
        ar7.add("Patel");
        ar7.add("Rajput");
        ar7.add("Raval");
        ar7.add("Reddy");
        ar7.add("Nair");
        ar7.add("Rathod");
        ar7.add("Rao");
        ar7.add("Sharma");
        ar7.add("Suthar");
        ar7.add("Sheth");
        ar7.add("Soni");
        ar7.add("Thakor");
        ar7.add("Thakkar");
        ar7.add("Trivedi");
        ar7.add("Vyas");
        ar7.add("Vanand");
        ar7.add("Yadav");



        sp8=(Spinner)rootview.findViewById(R.id.spinnermothertongue);
        ar8=new ArrayList<>();
        ar8.add("Doesn't Matter");
        ar8.add("Hindi");
        ar8.add("Panjabi");
        ar8.add("Haryanvi");
        ar8.add("Himachali");
        ar8.add("Sindhi");
        ar8.add("Marathi");
        ar8.add("Gujarati");
        ar8.add("Malayalam");
        ar8.add("Tamil");
        ar8.add("Telugu");
        ar8.add("Bengali");
        ar8.add("English");
        ar8.add("Kannada");



        sp9=(Spinner)rootview.findViewById(R.id.spinnereducation);
        ar9=new ArrayList<>();
        ar9.add("Doesn't Matter");
        ar9.add("B.Arch");
        ar9.add("B.Des");
        ar9.add("B.E/B.Tech");
        ar9.add("Bhavnagar");
        ar9.add("B.Pharma");
        ar9.add("M.E/M.Tech");
        ar9.add("M.Pharma");
        ar9.add("M.S");
        ar9.add("B.IT");
        ar9.add("BCA");
        ar9.add("MCA");
        ar9.add("B.Com");
        ar9.add("M.Com");
        ar9.add("CA");
        ar9.add("BBA");
        ar9.add("MBA");
        ar9.add("BAMS");
        ar9.add("BHMS");
        ar9.add("MBBS");
        ar9.add("M.Com");
        ar9.add("MDS");
        ar9.add("LLB");
        ar9.add("PHD");
        ar9.add("High School");
        ar9.add("Diploma");




        sp10=(Spinner)rootview.findViewById(R.id.spinneroccupation);
        ar10=new ArrayList<>();
        ar10.add("Doesn't Matter");
        ar10.add("Government Sector");
        ar10.add("Civil Services");
        ar10.add("Defence");
        ar10.add("Architecture");
        ar10.add("Airline");
        ar10.add("Armed Force");
        ar10.add("Busness");
        ar10.add("Not working");
        ar10.add("Doctor");
        ar10.add("Engineering");
        ar10.add("Hospitality");
        ar10.add("Law");
        ar10.add("Navy");
        ar10.add("Merchantecture");
        ar10.add("Medical and Healthcare");
        ar10.add("Softwere and IT");




        sp11=(Spinner)rootview.findViewById(R.id.spinnerminincome);
        ar11=new ArrayList<>();

        ar11.add("Rs.0-1 Lakh");
        ar11.add("Rs.1-1 Lakh");
        ar11.add("Rs.2-3 Lakh");
        ar11.add("Rs.3-4 Lakh");
        ar11.add("Rs.4-5 Lakh");
        ar11.add("Rs.5-6 Lakh");
        ar11.add("Rs.6-10 Lakh");
        ar11.add("Rs.10-15 Lakh");
        ar11.add("Rs.15-20 Lakh");
        ar11.add("Rs.20-25 Lakh");
        ar11.add("Rs.25-30 Lakh");
        ar11.add("Rs.30-40 Lakh");
        ar11.add("Rs.40-50 Lakh");
        ar11.add("Rs.50-60 Lakh");
        ar11.add("Rs.60-70 Lakh");
        ar11.add("Rs.70-1 crore");
        ar11.add("Rs. 1 crore & above");

        sp12=(Spinner)rootview.findViewById(R.id.spinnermaxincome);
        ar12=new ArrayList<>();

        ar12.add("Rs.0-1 Lakh");
        ar12.add("Rs.1-1 Lakh");
        ar12.add("Rs.2-3 Lakh");
        ar12.add("Rs.3-4 Lakh");
        ar12.add("Rs.4-5 Lakh");
        ar12.add("Rs.5-6 Lakh");
        ar12.add("Rs.6-10 Lakh");
        ar12.add("Rs.10-15 Lakh");
        ar12.add("Rs.15-20 Lakh");
        ar12.add("Rs.20-25 Lakh");
        ar12.add("Rs.25-30 Lakh");
        ar12.add("Rs.30-40 Lakh");
        ar12.add("Rs.40-50 Lakh");
        ar12.add("Rs.50-60 Lakh");
        ar12.add("Rs.60-70 Lakh");
        ar12.add("Rs.70-1 crore");
        ar12.add("Rs. 1 crore & above");




        ArrayAdapter ad=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar);
        sp.setAdapter(ad);


        ArrayAdapter ad1=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar1);
        sp1.setAdapter(ad1);

        ArrayAdapter ad2=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar2);
        sp2.setAdapter(ad2);

        ArrayAdapter ad3=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar3);
        sp3.setAdapter(ad3);


        ArrayAdapter ad4=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar4);
        sp4.setAdapter(ad4);


        ArrayAdapter ad5=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar5);
        sp5.setAdapter(ad5);

        ArrayAdapter ad6=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar6);
        sp6.setAdapter(ad6);

        ArrayAdapter ad7=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar7);
        sp7.setAdapter(ad7);


        ArrayAdapter ad8=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar8);
        sp8.setAdapter(ad8);


        ArrayAdapter ad9=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar9);
        sp9.setAdapter(ad9);

        ArrayAdapter ad10=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar10);
        sp10.setAdapter(ad10);

        ArrayAdapter ad11=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar11);
        sp11.setAdapter(ad11);


        ArrayAdapter ad12=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,ar12);
        sp12.setAdapter(ad12);



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar1.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar4.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar5.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar6.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar7.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        sp8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar8.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar9.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar10.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        sp11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar11.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String b=ar12.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return rootview;
    }
}

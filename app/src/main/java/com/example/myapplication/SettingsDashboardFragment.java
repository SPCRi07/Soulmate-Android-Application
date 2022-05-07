package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class SettingsDashboardFragment extends Fragment {


    View rootView;
     ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings_dashboard, container, false);
        listView=(ListView) rootView.findViewById(R.id.list);

        String[] title ={"Edit Profile","Privacy Setting","Change Password","Security","MyStatistics","Delete Your Account","Logout"};

        Integer[] image={
                R.drawable.editprofile, R.drawable.private1,
                R.drawable.key,R.drawable.ic_password ,R.drawable.ic_checklist,R.drawable.me, R.drawable.turn_on};

        listView=(ListView)rootView.findViewById(R.id.list);

        UserProfileListAdapter adapter=new UserProfileListAdapter(getActivity(),title,image);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                if(position == 0) {
                    Intent intent1=new Intent(getActivity().getApplication(), editprofile.class);
                    startActivity(intent1);
                }
                else if(position == 1) {
                    Intent intent1=new Intent(getActivity().getApplication(), privacy.class);
                    startActivity(intent1);
                }
                else if(position == 2) {
                    Intent intent2=new Intent(getActivity().getApplication(), changepassword.class);
                    startActivity(intent2);
                }

                else if(position == 3) {
                    Intent intent2=new Intent(getActivity().getApplication(), securitypassword.class);
                    startActivity(intent2);
                }

                else if(position == 4) {
                    Intent intent3=new Intent(getActivity().getApplication(), mystatistics.class);
                    startActivity(intent3);
                }
                else if(position == 5) {
                    Intent intent31=new Intent(getActivity().getApplication(), deleteAccount.class);
                    startActivity(intent31);
                }
                else if (position==6){
                    Intent intent4=new Intent(getActivity().getApplication(), Feedbacklogout.class);
                    startActivity(intent4);
                }
            }
        });

        return rootView;
    }
}

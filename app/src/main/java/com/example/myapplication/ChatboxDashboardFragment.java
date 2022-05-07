package com.example.myapplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

 public class  ChatboxDashboardFragment extends Fragment implements TabLayout.OnTabSelectedListener {


     TabLayout tabLayout;
       Toolbar toolbar;
    //This is our viewPager
     ViewPager viewPager;
    TabLayout.Tab tab1,tab2,tab3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chatbox_dashboard, container, false);

        toolbar =  (Toolbar)rootView.findViewById(R.id.toolbar1);
        AppCompatActivity activity=(AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tab1=tabLayout.newTab().setText("Request");
        tab2=tabLayout.newTab().setText("Chat");
        tab3=tabLayout.newTab().setText("PhotoRequest");

        //Adding the tabs using addTab() method//
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        //Creating our pager adapter
        Pager adapter = new Pager(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);
            return rootView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}






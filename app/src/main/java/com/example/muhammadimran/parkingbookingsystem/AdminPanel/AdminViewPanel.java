package com.example.muhammadimran.parkingbookingsystem.AdminPanel;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.muhammadimran.parkingbookingsystem.Adapters.fragmentsAdapter;
import com.example.muhammadimran.parkingbookingsystem.R;

import java.util.ArrayList;

public class AdminViewPanel extends AppCompatActivity {
    ArrayList<Fragment> arrayList;
    fragmentsAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_panel);
        tabLayout = (TabLayout) findViewById(R.id.Admin_sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.UserParking);
        arrayList = new ArrayList<>();
        arrayList.add(new RegesterUsersList());
        arrayList.add(new BookedParkingSlots());

        tabLayout.addTab(tabLayout.newTab().setText("Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Registered Parking"));


        adapter = new fragmentsAdapter(getSupportFragmentManager(), arrayList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        new RegesterUsersList();
                        break;
                    case 1:
                        new BookedParkingSlots();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

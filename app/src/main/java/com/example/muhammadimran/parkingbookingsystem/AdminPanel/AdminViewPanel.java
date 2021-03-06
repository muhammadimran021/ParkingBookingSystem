package com.example.muhammadimran.parkingbookingsystem.AdminPanel;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.muhammadimran.parkingbookingsystem.Adapters.fragmentsAdapter;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminViewPanel extends AppCompatActivity {
    ArrayList<Fragment> arrayList;
    fragmentsAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_panel);
        mAuth = FirebaseAuth.getInstance();
        tabLayout = (TabLayout) findViewById(R.id.Admin_sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.UserParking);
        arrayList = new ArrayList<>();
        arrayList.add(new RegesterUsersList());
        arrayList.add(new BookedParkingSlots());
        arrayList.add(new UserFeedback());

        tabLayout.addTab(tabLayout.newTab().setText("Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Registered Parking"));
        tabLayout.addTab(tabLayout.newTab().setText("Users Feedback"));


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
                    case 2:
                        new UserFeedback();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

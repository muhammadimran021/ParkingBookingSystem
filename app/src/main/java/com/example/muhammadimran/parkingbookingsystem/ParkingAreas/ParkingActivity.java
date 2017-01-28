package com.example.muhammadimran.parkingbookingsystem.ParkingAreas;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.muhammadimran.parkingbookingsystem.Adapters.parkingAdapter;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.example.muhammadimran.parkingbookingsystem.Models.userfeedbackModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParkingActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Fragment> arrayList;
    parkingAdapter adapter;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tabLayout = (TabLayout) findViewById(R.id.User_sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.User_viewpager);
        arrayList = new ArrayList<>();
        arrayList.add(new HomeParking());
        arrayList.add(new OfficeParking());

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Office"));


        adapter = new parkingAdapter(getSupportFragmentManager(), arrayList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        new HomeParking();
                        break;
                    case 1:
                        new OfficeParking();
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
            case R.id.feedback:
                AlertDialog.Builder builder = new AlertDialog.Builder(ParkingActivity.this);
                View dialogView = this.getLayoutInflater().inflate(R.layout.feedback_view, null, false);
                builder.setTitle("Select Slot.");
                builder.setView(dialogView);
                EditText feedback = (EditText) dialogView.findViewById(R.id.UserFeedBack);

                builder.setPositiveButton("Ok", (dialogInterface, i) -> mDatabase.child("user-info").child(mAuth.getCurrentUser().getUid().toString()).child("fname").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String username = dataSnapshot.getValue().toString();
                        String feedbacK = feedback.getText().toString();
                        userfeedbackModel model = new userfeedbackModel(feedbacK, username);
                        mDatabase.child("FeedBack").child(mAuth.getCurrentUser().getUid()).push().setValue(model);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }));
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.muhammadimran.parkingbookingsystem.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class parkingAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;


    public parkingAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}

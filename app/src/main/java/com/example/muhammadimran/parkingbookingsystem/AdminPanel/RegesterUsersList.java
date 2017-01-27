package com.example.muhammadimran.parkingbookingsystem.AdminPanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadimran.parkingbookingsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegesterUsersList extends Fragment {


    public RegesterUsersList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regester_users_list, container, false);

        return view;
    }

}

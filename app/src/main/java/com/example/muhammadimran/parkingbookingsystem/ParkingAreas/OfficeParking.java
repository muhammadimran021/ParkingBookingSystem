package com.example.muhammadimran.parkingbookingsystem.ParkingAreas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadimran.parkingbookingsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficeParking extends Fragment {


    public OfficeParking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_office_parking, container, false);
    }

}

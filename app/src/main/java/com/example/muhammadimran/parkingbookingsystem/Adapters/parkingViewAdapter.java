package com.example.muhammadimran.parkingbookingsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.parkingbookingsystem.ParkingAreas.TimeModel;
import com.example.muhammadimran.parkingbookingsystem.R;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/28/2017.
 */

public class parkingViewAdapter extends BaseAdapter {
    ArrayList<TimeModel> timeModelArrayList;
    Context context;

    public parkingViewAdapter(ArrayList<TimeModel> timeModelArrayList, Context context) {
        this.timeModelArrayList = timeModelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return timeModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return timeModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.parkin_view_layout, null);
        TextView parkingbook = (TextView) view1.findViewById(R.id.booked_parking);
        TextView time = (TextView) view1.findViewById(R.id.parkingTime);
        TextView mint = (TextView) view1.findViewById(R.id.end_time);
        TextView date = (TextView) view1.findViewById(R.id.date);

        TimeModel timeModel = timeModelArrayList.get(i);
        parkingbook.setText("Booked: " + timeModel.getParking());
        time.setText("Start Time: " + timeModel.getStart_time());
        mint.setText("End Time: " + timeModel.getEnd_time());
        date.setText("Date: " + timeModel.getDate());


        return view1;
    }
}

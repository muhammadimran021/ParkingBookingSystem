package com.example.muhammadimran.parkingbookingsystem.AdminPanel;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.muhammadimran.parkingbookingsystem.Adapters.parkingViewAdapter;
import com.example.muhammadimran.parkingbookingsystem.Models.TimeModel;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedParkingSlots extends Fragment {

    private ArrayList<TimeModel> timeModelArrayList;
    private parkingViewAdapter adapter;
    private ListView listView;
    private DatabaseReference mDatabase;

    public BookedParkingSlots() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_parking_slots, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) view.findViewById(R.id.BookedParkinSlots);
        timeModelArrayList = new ArrayList<>();
        adapter = new parkingViewAdapter(timeModelArrayList, getContext());
        listView.setAdapter(adapter);

        mDatabase.child("Reserve-parking").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TimeModel model = dataSnapshot.getValue(TimeModel.class);
                timeModelArrayList.add(model);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void deleteSlots() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("are you sure to want to delete parking?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDatabase.child("Reserve-parking").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int position = 0;
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    if (position == i) {
                                        DatabaseReference ref = data.getRef();
                                        ref.removeValue();
                                        timeModelArrayList.remove(i);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.create().show();
            }
        });
    }
}
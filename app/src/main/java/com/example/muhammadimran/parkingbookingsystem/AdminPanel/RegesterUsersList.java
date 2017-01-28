package com.example.muhammadimran.parkingbookingsystem.AdminPanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.muhammadimran.parkingbookingsystem.Adapters.UsersAdapters;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.example.muhammadimran.parkingbookingsystem.UserPanel.UserModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegesterUsersList extends Fragment {
    private ArrayList<UserModel> arrayList;
    private UsersAdapters usersAdapters;
    private ListView listView;
    private DatabaseReference mDatabase;

    public RegesterUsersList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regester_users_list, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) view.findViewById(R.id.RegesterUsersListss);
        arrayList = new ArrayList<>();
        usersAdapters = new UsersAdapters(arrayList, getContext());
        listView.setAdapter(usersAdapters);

        mDatabase.child("user-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("USer-Info", dataSnapshot.getValue().toString());
                UserModel model = dataSnapshot.getValue(UserModel.class);
                arrayList.add(model);
                usersAdapters.notifyDataSetChanged();
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
                Log.d("TAg", databaseError.getMessage());
            }
        });

        return view;
    }

}

package com.example.muhammadimran.parkingbookingsystem.AdminPanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.muhammadimran.parkingbookingsystem.Adapters.feedbackAdapter;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.example.muhammadimran.parkingbookingsystem.userfeedbackModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFeedback extends Fragment {
    ArrayList<userfeedbackModel> userfeedbackModels;
    feedbackAdapter adapter;
    ListView listView;
    DatabaseReference database;

    public UserFeedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_feedback, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        listView = (ListView) view.findViewById(R.id.feedbackList);
        userfeedbackModels = new ArrayList<>();
        adapter = new feedbackAdapter(userfeedbackModels, getContext());
        listView.setAdapter(adapter);


        database.child("FeedBack").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("TAGUSER: ", dataSnapshot.getValue().toString());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    userfeedbackModel model = dataSnapshot1.getValue(userfeedbackModel.class);
                    userfeedbackModels.add(model);
                    adapter.notifyDataSetChanged();
                }

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

}

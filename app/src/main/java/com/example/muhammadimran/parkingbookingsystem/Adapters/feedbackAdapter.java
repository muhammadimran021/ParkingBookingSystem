package com.example.muhammadimran.parkingbookingsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.parkingbookingsystem.R;
import com.example.muhammadimran.parkingbookingsystem.userfeedbackModel;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/28/2017.
 */

public class feedbackAdapter extends BaseAdapter {

    ArrayList<userfeedbackModel> models;
    Context context;

    public feedbackAdapter(ArrayList<userfeedbackModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.user_feedback, null);
        TextView username = (TextView) view1.findViewById(R.id.username_feedback);
        TextView feedback = (TextView) view1.findViewById(R.id.feedback_of_user);

        userfeedbackModel userfeedbackModel1 = models.get(i);
        username.setText("name: " + userfeedbackModel1.getUser_feedback_name());
        feedback.setText("feedback: " + userfeedbackModel1.getFeedback());

        return view1;
    }
}

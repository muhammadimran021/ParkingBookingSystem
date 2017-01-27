package com.example.muhammadimran.parkingbookingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.parkingbookingsystem.UserPanel.UserModel;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class UsersAdapter extends BaseAdapter {
    ArrayList<UserModel> userModels;
    Context context;

    public UsersAdapter(ArrayList<UserModel> userModels, Context context) {
        this.userModels = userModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.users_view_list, null);
        TextView name = (TextView) view1.findViewById(R.id.Username);
        TextView email = (TextView) view1.findViewById(R.id.User_email);
        TextView contact = (TextView) view1.findViewById(R.id.usercontact);

        UserModel model = userModels.get(i);
        name.setText("Name: " + model.getFname() + " " + model.getLname());
        email.setText("Email: " + model.getEmail());
        contact.setText("Contact" + model.getContact());
        return view1;
    }
}

package com.example.muhammadimran.parkingbookingsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.parkingbookingsystem.R;
import com.example.muhammadimran.parkingbookingsystem.UserPanel.UserModel;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/27/2017.
 */

public class UsersAdapters extends BaseAdapter {
    ArrayList<UserModel> userModelArrayList;
    Context context;

    public UsersAdapters(ArrayList<UserModel> userModelArrayList, Context context) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return userModelArrayList.get(i);
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

        UserModel model = userModelArrayList.get(i);
        name.setText(model.getFname());
        email.setText(model.getEmail());
        contact.setText(model.getContact());

        return view1;
    }
}

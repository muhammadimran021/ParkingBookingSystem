package com.example.muhammadimran.parkingbookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.example.muhammadimran.parkingbookingsystem.AdminPanel.AdminRegestrationPanel;
import com.example.muhammadimran.parkingbookingsystem.UserPanel.UserRegestrationPanel;

public class MainActivity extends AppCompatActivity {
    CardView Admin, User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Admin = (CardView) findViewById(R.id.AdminPanel);
        User = (CardView) findViewById(R.id.UserPanel);

        AdminPanel();
        UserPaner();
    }

    public void AdminPanel() {
        Admin.setOnClickListener(view -> {
            Intent admin = new Intent(MainActivity.this, AdminRegestrationPanel.class);
            startActivity(admin);
        });
    }

    public void UserPaner() {
        User.setOnClickListener(view -> {
            Intent user = new Intent(MainActivity.this, UserRegestrationPanel.class);
            startActivity(user);
        });
    }
}

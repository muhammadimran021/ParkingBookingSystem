package com.example.muhammadimran.parkingbookingsystem.AdminPanel;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminRegestrationPanel extends AppCompatActivity {
    EditText AdminEmail, AdminPassword;
    Button AdminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_regestration_panel);
        AdminEmail = (EditText) findViewById(R.id.Adminemail);
        AdminPassword = (EditText) findViewById(R.id.AdminPassword);
        AdminLoginButton = (Button) findViewById(R.id.AdminLoginButton);
        AdminLoginButton.setOnClickListener(view -> {
            if (AdminEmail.getText().toString().equals("admin@gmail.com") && AdminPassword.getText().toString().equals("admin")) {
                Intent intent = new Intent(AdminRegestrationPanel.this, AdminViewPanel.class);
                startActivity(intent);
            } else {
                Toast.makeText(AdminRegestrationPanel.this, "Sorry invalid Email or password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

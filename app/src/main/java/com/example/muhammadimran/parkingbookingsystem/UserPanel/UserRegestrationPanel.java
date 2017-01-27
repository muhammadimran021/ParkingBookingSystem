package com.example.muhammadimran.parkingbookingsystem.UserPanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadimran.parkingbookingsystem.ParkingAreas.ParkingActivity;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegestrationPanel extends AppCompatActivity {
    TextView noAccount;
    EditText userEmail, UserPassword;
    Button signIn;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regestration_panel);

        mAuth = FirebaseAuth.getInstance();
        noAccount = (TextView) findViewById(R.id.UserAccount);
        userEmail = (EditText) findViewById(R.id.UserEmail);
        UserPassword = (EditText) findViewById(R.id.userPassword);
        signIn = (Button) findViewById(R.id.userLoginButton);
        dialog = new ProgressDialog(this);

        Login();
        CreateAccount();
    }

    public void Login() {
        signIn.setOnClickListener(view -> {
            dialog.setMessage("LogIn...");
            dialog.show();
            String user_email = userEmail.getText().toString();
            String user_password = UserPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(user_email, user_password).addOnSuccessListener(authResult -> {
                        userEmail.setText("");
                        UserPassword.setText("");
                        Intent intent = new Intent(this, ParkingActivity.class);
                        startActivity(intent);
                        dialog.dismiss();

                    }

            ).addOnFailureListener(e -> {
                dialog.dismiss();
                Toast.makeText(UserRegestrationPanel.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void CreateAccount() {
        noAccount.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.userframe, new UserSignUp());
            fragmentTransaction.commit();
        });
    }
}

package com.example.muhammadimran.parkingbookingsystem.UserPanel;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammadimran.parkingbookingsystem.Models.UserModel;
import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSignUp extends Fragment {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    EditText firsname, lastname, email, password, confirmpassword, dateOfBirth, contact;
    Button signUp;
    ProgressDialog dialog;

    public UserSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_sign_up, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(getActivity());

        firsname = (EditText) view.findViewById(R.id.FirstName);
        lastname = (EditText) view.findViewById(R.id.LastName);
        email = (EditText) view.findViewById(R.id.Email);
        password = (EditText) view.findViewById(R.id.Password);
        confirmpassword = (EditText) view.findViewById(R.id.ConfirmPassword);
        dateOfBirth = (EditText) view.findViewById(R.id.DateOfBirth);
        contact = (EditText) view.findViewById(R.id.Contact);
//Button
        signUp = (Button) view.findViewById(R.id.SignUp);
        ClickOnSignUp();
        return view;
    }

    public void ClickOnSignUp() {
        signUp.setOnClickListener(view -> {
            dialog.setMessage("Plz Wait...");
            dialog.show();
            String first_name = firsname.getText().toString();
            String last_name = lastname.getText().toString();
            String user_email = email.getText().toString();
            String user_password = password.getText().toString();
            String confirm_password = confirmpassword.getText().toString();
            String user_dateofbirth = dateOfBirth.getText().toString();
            String user_contact = contact.getText().toString();


            if (user_password.equals(confirm_password)) {

                mAuth.createUserWithEmailAndPassword(user_email, confirm_password).addOnSuccessListener(authResult -> {
                    String UUID = authResult.getUser().getUid().toString();
                    UserModel userModel = new UserModel(first_name, last_name, user_email, user_password, confirm_password, user_dateofbirth, user_contact);
                    mDatabase.child("user-info").child(UUID).setValue(userModel);
                    firsname.setText("");
                    lastname.setText("");
                    email.setText("");
                    password.setText("");
                    confirmpassword.setText("");
                    dateOfBirth.setText("");
                    contact.setText("");
                    Intent i = new Intent(getContext(), UserRegestrationPanel.class);
                    startActivity(i);
                    getActivity().finish();
                    dialog.dismiss();
                }).addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });

            } else {
                Toast.makeText(getActivity(), "Sorry registration failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.muhammadimran.parkingbookingsystem.ParkingAreas;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadimran.parkingbookingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeParking extends Fragment {
    CardView paking1, parking2, parking3;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener dateset;
    TextView date;
    Spinner SelectedTime, SelectedMint;
    String taketime;
    String takehours;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    String takedate;
    ImageView done, done1, done3;
    ProgressDialog dialog;

    public HomeParking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_parking, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        paking1 = (CardView) view.findViewById(R.id.parking1);
        parking2 = (CardView) view.findViewById(R.id.parking2);
        parking3 = (CardView) view.findViewById(R.id.parking3);
        done = (ImageView) view.findViewById(R.id.done);
        done1 = (ImageView) view.findViewById(R.id.done1);
        done3 = (ImageView) view.findViewById(R.id.done3);
        paking1.setOnClickListener(view1 -> {

            mDatabase.child("Reserve-parking").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("parking-slot-1")) {
                        // Toast.makeText(getContext(), "Sorry parking already booked!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Selectparking1();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        });

        clickOnSecondParking();
        clickOnThirdParking();
        return view;
    }

    public void Selectparking1() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.parkingbooking, null, false);
        builder.setTitle("Select Slot.");
        builder.setView(dialogView);
        date = (TextView) dialogView.findViewById(R.id.selectdate);
        SelectedTime = (Spinner) dialogView.findViewById(R.id.selectHours);
        SelectedMint = (Spinner) dialogView.findViewById(R.id.selectMint);

        //Time Work
        myCalendar = Calendar.getInstance();
        dateset = (datePicker, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updatelable();
        };
        date.setOnClickListener(view1 -> {
            new DatePickerDialog(getActivity(), dateset, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        });

        //Start hours
        SelectedTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                taketime = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SelectedMint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                takehours = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Plz wait...");
            String UUID = mAuth.getCurrentUser().getUid();

            TimeModel timeModel = new TimeModel(UUID, takedate, taketime, takehours, "Home parking slot 1");
            mDatabase.child("Reserve-parking").child("parking-slot-1").setValue(timeModel);
            done.setVisibility(View.VISIBLE);


        });

        builder.create().show();

    }


    public void updatelable() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        takedate = sdf.format(myCalendar.getTime());
        date.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Check Reserve Parking...");
        dialog.show();

        mDatabase.child("Reserve-parking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("parking-slot-1") || dataSnapshot.hasChild("Home parking-slot-2") || dataSnapshot.hasChild("Home parking-slot-3")) {

                    if (dataSnapshot.hasChild("parking-slot-1")) {
                        done.setVisibility(View.VISIBLE);
                    }
                    if (dataSnapshot.hasChild("parking-slot-2")) {
                        done1.setVisibility(View.VISIBLE);
                    }
                    if (dataSnapshot.hasChild("parking-slot-3")) {
                        done3.setVisibility(View.VISIBLE);
                    }
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
    }

    public void clickOnSecondParking() {
        parking2.setOnClickListener(view -> {
            mDatabase.child("Reserve-parking").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("parking-slot-2")) {
                        done1.setVisibility(View.VISIBLE);
                        //Toast.makeText(getContext(), "Sorry parking already booked!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Selectparking2();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        });
    }

    private void Selectparking2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.parkingbooking, null, false);
        builder.setTitle("Select Slot.");
        builder.setView(dialogView);
        date = (TextView) dialogView.findViewById(R.id.selectdate);
        SelectedTime = (Spinner) dialogView.findViewById(R.id.selectHours);
        SelectedMint = (Spinner) dialogView.findViewById(R.id.selectMint);

        //Time Work
        myCalendar = Calendar.getInstance();
        dateset = (datePicker, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updatelable();
        };
        date.setOnClickListener(view1 -> new DatePickerDialog(getActivity(), dateset, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        //Start hours
        SelectedTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                taketime = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SelectedMint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                takehours = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Plz wait...");
            String UUID = mAuth.getCurrentUser().getUid();

            TimeModel timeModel = new TimeModel(UUID, takedate, taketime, takehours, "parking slot 2");
            mDatabase.child("Reserve-parking").child("parking-slot-2").setValue(timeModel);
            done1.setVisibility(View.VISIBLE);


        });

        builder.create().show();
    }


    public void clickOnThirdParking() {
        parking3.setOnClickListener(view -> {
            mDatabase.child("Reserve-parking").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("parking-slot-3")) {
                        done3.setVisibility(View.VISIBLE);
                        //Toast.makeText(getContext(), "Sorry parking already booked!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        SelectThirdPlace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        });
    }

    public void SelectThirdPlace() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.parkingbooking, null, false);
        builder.setTitle("Select Slot.");
        builder.setView(dialogView);
        date = (TextView) dialogView.findViewById(R.id.selectdate);
        SelectedTime = (Spinner) dialogView.findViewById(R.id.selectHours);
        SelectedMint = (Spinner) dialogView.findViewById(R.id.selectMint);

        //Time Work
        myCalendar = Calendar.getInstance();
        dateset = (datePicker, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updatelable();
        };
        date.setOnClickListener(view1 -> {
            new DatePickerDialog(getActivity(), dateset, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();

        });

        //Start hours
        SelectedTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                taketime = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SelectedMint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                takehours = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Plz wait...");
            String UUID = mAuth.getCurrentUser().getUid();

            TimeModel timeModel = new TimeModel(UUID, takedate, taketime, takehours, "parking slot 3");
            mDatabase.child("Reserve-parking").child("parking-slot-3").setValue(timeModel);
            done1.setVisibility(View.VISIBLE);


        });

        builder.create().show();
    }
}

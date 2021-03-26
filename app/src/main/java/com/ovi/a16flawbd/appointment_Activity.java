package com.ovi.a16flawbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ovi.a16flawbd.ModelClasses.Appointment_info;
import com.ovi.a16flawbd.ModelClasses.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class appointment_Activity extends AppCompatActivity {

    String receiveruserID, sendSenderName, senderuserID;
    List<UserModel> userModelList;
    TextView name;
    Button button, cancel;

    DatabaseReference databaseReference;
    Appointment_info appointment_info;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_);


        receiveruserID = getIntent().getStringExtra("lawyerName"); // lawyer id
        senderuserID = FirebaseAuth.getInstance().getCurrentUser().getUid(); // simple user id

        name = findViewById(R.id.name);
        name.setText(receiveruserID);

        cancel = findViewById(R.id.cancel);
        button = findViewById(R.id.button);
        userModelList = new ArrayList<>();
        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("BaatCheet/Users/");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.7), (int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);



      //send appointment info to firebase


        databaseReference = FirebaseDatabase.getInstance().getReference("BaatCheet/");
        appointment_info = new Appointment_info();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("got_appointment", "No");
                hashMap.put("which_lawyer", receiveruserID);

                databaseReference.child("appointment").child(senderuserID).setValue(hashMap);
                finish();
            }
        });


    }




}
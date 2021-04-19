package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class ServiceMenu extends AppCompatActivity {
    ImageButton med;
    ImageButton doc;
    UserInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_service);
        Intent intent = getIntent();
        user = (UserInfo) intent.getSerializableExtra("User");
        med = (ImageButton)findViewById(R.id.medicine);
        doc = (ImageButton)findViewById(R.id.doctor);
        UserInfo ui = (UserInfo) getIntent().getSerializableExtra("User");
        doc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceMenu.this, Doctors_List.class);
                i.putExtra("User", (Serializable) ui);
                startActivity(i);
            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceMenu.this, Medicines.class);
                i.putExtra("User", (Serializable) ui);
                startActivity(i);
            }
        });
    }


}
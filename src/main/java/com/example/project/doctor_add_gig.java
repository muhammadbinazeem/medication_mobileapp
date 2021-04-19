package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class doctor_add_gig extends AppCompatActivity {
    String name;

    final ArrayList<Doctor_gig> temp = new ArrayList<Doctor_gig>();
    final String[] desctemp = {null};
    final String[] doc_spec = {null};
    final String[] titleTemp = {null};
    final String[] nameTemp = {null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_doctor_gigs);



        Intent intent = getIntent();
        name = intent.getExtras().getString("Username");
        Log.println(Log.DEBUG, "in doctor***",name);
    }


    public void adddetails() {
        EditText description = findViewById(R.id.description);
        EditText doctor_specalization = findViewById(R.id.doctor_specalization);
        EditText titlte = findViewById(R.id.titlte);

        Doctor_gig a = new Doctor_gig(description.getText().toString(), doctor_specalization.getText().toString()
        ,titlte.getText().toString(), this.name);
        a.StoreData();
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.button3) {
            adddetails();
        };
    }
}

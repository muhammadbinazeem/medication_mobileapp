package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.project.adapters.DoctorTileAdapter;
import com.example.project.adapters.TileAdapter;
import com.example.project.models.Doctor_gig;
import com.example.project.models.medicine_gig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Medicines extends AppCompatActivity {
    TileAdapter ta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        RecyclerView populateList = (RecyclerView) findViewById(R.id.MedRecyclerList);
        populateList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<medicine_gig> arr = new ArrayList<medicine_gig>();
        UserInfo ui = (UserInfo) getIntent().getSerializableExtra("User");
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("medicine_data");
        ref.addValueEventListener(new ValueEventListener() {

            final String[] medicine_name = {null};
            final String[] medicine_size = {null};
            final String[] pharmacy_name = {null};
            final String[] price = {null};
            final String[] quantity = {null};


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapsho1t : snapshot.getChildren()) {
                    medicine_name[0] =  snapsho1t.child("medicine_name").getValue().toString();
                    medicine_size[0] = snapsho1t.child("medicine_size").getValue().toString();
                    pharmacy_name[0] = snapsho1t.child("pharmacy_name").getValue().toString();
                    price[0] = snapsho1t.child("price").getValue().toString();
                    quantity[0] = snapsho1t.child("quantity").getValue().toString();

                    medicine_gig temp = new medicine_gig(medicine_name[0], pharmacy_name[0], medicine_size[0]+"mg", "Rs."+price[0], "Quantity: "+quantity[0]);
                    arr.add(temp);
                }
                ta = new TileAdapter(arr,ui);
                populateList.setAdapter(ta);

//                Log.println(Log.DEBUG, "afterretreivedatasdf**", "" + dgList.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
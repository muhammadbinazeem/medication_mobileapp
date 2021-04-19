package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
import java.util.HashSet;

public class after_signin extends AppCompatActivity {

    private static final int REQUEST_CODE = 2;
    UserInfo User = new UserInfo();
    String Username;
    final ArrayList<Doctor_gig> temp = new ArrayList<Doctor_gig>();
    final ArrayList<medicine_gig> temp1 = new ArrayList<medicine_gig>();
    final String[] desctemp = {null};
    final String[] doc_spec = {null};
    final String[] titleTemp = {null};
    final String[] nameTemp = {null};
    final String[] nameTemp1 = {null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gigrecycleriew);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        User = (UserInfo) intent.getSerializableExtra("User");
        Log.println(Log.DEBUG, "in aftersignin****",User.Name);

        if(User.Type.equals("Doctor")) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("doctor_data");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    temp.clear();
                    for (DataSnapshot snapsho1t : snapshot.getChildren()) {
                        desctemp[0] =  snapsho1t.child("description").getValue().toString();
                        doc_spec[0] = snapsho1t.child("doctor_specalization").getValue().toString();
                        titleTemp[0] = snapsho1t.child("title").getValue().toString();
                        nameTemp[0] = snapsho1t.child("name").getValue().toString();

                        Doctor_gig add = new Doctor_gig(desctemp[0], doc_spec[0], titleTemp[0], nameTemp[0]);
                        Log.println(Log.DEBUG, "in doctor spec***",doc_spec[0]);


                        if(!temp.contains(add)){
                            Log.println(Log.DEBUG, "if ***",nameTemp[0] + " " +User.Name);
                            if (nameTemp[0].equals(User.Name)) {
                                temp.add(add);
                            }
                        }
                    }
                    Log.println(Log.DEBUG,"wfddsf ",""+temp.size());
                    CreateView();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if (User.Type.equals("Pharmacy")) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("medicine_data");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    temp1.clear();
                    for (DataSnapshot snapsho1t : snapshot.getChildren()) {

                        desctemp[0] =  snapsho1t.child("medicine_name").getValue().toString();
                        doc_spec[0] = snapsho1t.child("pharmacy_name").getValue().toString();
                        titleTemp[0] = snapsho1t.child("medicine_size").getValue().toString();
                        nameTemp[0] = snapsho1t.child("price").getValue().toString();
                        nameTemp1[0] = snapsho1t.child("quantity").getValue().toString();

                        medicine_gig add = new medicine_gig(desctemp[0], doc_spec[0], titleTemp[0], nameTemp[0], nameTemp1[0]);

                        if(!temp1.contains(add)){
                            if (doc_spec[0].equals(User.Name)) {
                                temp1.add(add);
                            }
                        }
                    }
                    Log.println(Log.DEBUG, "in medicine spec***",""+temp.size());
                    CreateView1();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                add();
                return true;
            case R.id.edit:
                Toast.makeText(this, "Logout 1", Toast.LENGTH_LONG).show();
                edit();
                return true;
            case R.id.logout:
                Toast.makeText(this, "Logout 1", Toast.LENGTH_LONG).show();
                log_out();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreateView() {
        RecyclerView temp = (RecyclerView) findViewById(R.id.recycle);
        temp.setLayoutManager(new LinearLayoutManager(this));
        temp.setAdapter(new gigRecyclerView (this.temp));
    }

    private void CreateView1() {
        RecyclerView temp = (RecyclerView) findViewById(R.id.recycle);
        temp.setLayoutManager(new LinearLayoutManager(this));
        temp.setAdapter(new medicine_gigRecyclerview (this.temp1));
    }

    private void log_out() {
        Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show();
        Log.println(Log.DEBUG,"log_out**","logout");
        Intent intent = new Intent();
        startActivity(intent);
    }

    private void edit() {
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("User",User);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void add() {
        if (User.Type.equals("Pharmacy")) {
            Intent intent = new Intent(this, medicine_add_gig.class);
            intent.putExtra("Username",User.Name);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (User.Type.equals("Doctor")) {
            Intent intent = new Intent(this, doctor_add_gig.class);
            intent.putExtra("Username",User.Name);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}

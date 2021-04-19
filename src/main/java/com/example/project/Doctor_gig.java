package com.example.project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Doctor_gig {

    String description;
    String doctor_specalization;
    String title;
    String name;

    Doctor_gig() {

    }

    Doctor_gig(String disc, String spec, String title, String name) {
        this.description = disc;
        this.doctor_specalization = spec;
        this.title = title;
        this.name = name;
    }

    void StoreData() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("doctor_data");

        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("description", this.description);
        userMap.put("doctor_specalization", this.doctor_specalization);
        userMap.put("title", this.title);
        userMap.put("name", this.name);

        root.push().setValue(userMap);
    }
}

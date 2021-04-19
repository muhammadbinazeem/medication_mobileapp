package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.models.Doctor;
import com.example.project.models.Doctor_gig;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Doctors_Gig extends AppCompatActivity {

  ImageView dp;
  TextView title;
  TextView name;
  TextView specaiality;
  TextView desc;
  RatingBar rb;
  private AdView mAdView;
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_doctors_gig);
    UserInfo ui = (UserInfo) getIntent().getSerializableExtra("User");
    Doctor_gig dc = (Doctor_gig)getIntent().getSerializableExtra("Doctor");
    String n = ui.Name;
    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });

    mAdView = findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);

    //UerInfois here now
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("appointments");

    HashMap<String, String> userMap = new HashMap<>();
    userMap.put("doctor_name", dc.getName());
    userMap.put("user", ui.Name);

    root.push().setValue(userMap);

    dp = (ImageView) findViewById(R.id.docprofileimage);
    title = (TextView) findViewById((R.id.title));
    name = (TextView) findViewById((R.id.name));
    specaiality = (TextView) findViewById(R.id.specialization);
    desc = (TextView) findViewById(R.id.desc);
    rb = (RatingBar) findViewById(R.id.ratingBar);
    Log.d("Doctor", dc.getName());
    title.setText(dc.getName());
    name.setText(dc.getTitle());
    specaiality.setText(dc.getDoctor_specalization());
    desc.setText(dc.getDescription());
   Button btn=(Button) findViewById(R.id.button);

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
     public void onClick(View v) {
        ArrayList<String> a=new ArrayList<>();
        String sendtext=ui.Name;
        String doctorname=dc.getTitle();
        a.add(sendtext);
        a.add(doctorname);
        Intent intent = new Intent(Doctors_Gig.this,Rating.class);
        intent.putExtra("data_of_docotr", a);
        startActivity(intent);
      }
    });
  }
}
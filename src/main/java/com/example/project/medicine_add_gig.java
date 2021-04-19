package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class medicine_add_gig extends AppCompatActivity {
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medicine_gig);
        Intent intent = getIntent();
        name = intent.getExtras().getString("Username");
        Log.println(Log.DEBUG, "in medicine***",name);
    }

    public void adddetails() {
        EditText medicine_name = findViewById(R.id.medicine_name);
        EditText medicine_size = findViewById(R.id.medicine_size);
        EditText price = findViewById(R.id.price);
        EditText quantity = findViewById(R.id.quantity);

        medicine_gig a = new medicine_gig(medicine_name.getText().toString(),this.name,medicine_size.getText().toString()
                ,price.getText().toString(), quantity.getText().toString());
        a.StoreData();
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.button4) {
            adddetails();
            //startActivity(new Intent(medicine_add_gig.this, medicine_gig.class));
        };
    }
}

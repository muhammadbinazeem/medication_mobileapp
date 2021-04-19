package com.example.project.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class medicine_gig {

    String medicine_name;
    String pharmacy_name;
    String medicine_size;
    String price;
    String quantity;

    medicine_gig() {

    }

    public medicine_gig(String med_name, String pharmacy_name, String med_size, String price, String Quantity) {
        this.medicine_name = med_name;
        this.pharmacy_name = pharmacy_name;
        this.medicine_size = med_size;
        this.price = price;
        this.quantity = Quantity;
    }

    void StoreData() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("medicine_data");

        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("medicine_name", this.medicine_name);
        userMap.put("pharmacy_name", this.pharmacy_name);
        userMap.put("medicine_size", this.medicine_size);
        userMap.put("price", this.price);
        userMap.put("quantity", this.quantity);

        root.push().setValue(userMap);
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public String getMedicine_size() {
        return medicine_size;
    }

    public void setMedicine_size(String medicine_size) {
        this.medicine_size = medicine_size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

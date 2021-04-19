package com.example.project.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.UserInfo;
import com.example.project.holders.TileHolder;
import com.example.project.models.medicine_gig;


import java.util.ArrayList;

public class TileAdapter extends RecyclerView.Adapter<TileHolder> {

    ArrayList<medicine_gig> categories;
    UserInfo ui;

    public TileAdapter(ArrayList<medicine_gig> arr, UserInfo u) {
        this.categories=arr;
        ui = u;
    }

    @NonNull
    @Override
    public TileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_tile,parent,false);
        return new TileHolder(view);
    }

    public void onBindViewHolder(@NonNull TileHolder holder, int position) {
        holder.hd.setText(categories.get(position).getMedicine_name());
        holder.pharma.setText(categories.get(position).getPharmacy_name());
        holder.price.setText(categories.get(position).getPrice());
        holder.size.setText(categories.get(position).getMedicine_size());
        holder.quantity.setText(categories.get(position).getQuantity());
        holder.ui.setter(ui);
        Context con = holder.getTextView().getContext();
        Log.d("ContextName", con.toString());
        int id = con.getResources().getIdentifier("pills",
                    "drawable", con.getPackageName());
        holder.img.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
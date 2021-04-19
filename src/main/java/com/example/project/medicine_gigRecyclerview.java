package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class medicine_gigRecyclerview  extends RecyclerView.Adapter {

    private ArrayList<medicine_gig> data;

    public medicine_gigRecyclerview(ArrayList<medicine_gig> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.medicine_gig, parent, false);
        return new medicine_gigRecyclerview.abcViewHolder(view);
    }

    public medicine_gig getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        medicine_gig tlt = getItem(position);
        ((medicine_gigRecyclerview.abcViewHolder) holder).medicine_nameT.setText(tlt.medicine_name);
        ((medicine_gigRecyclerview.abcViewHolder) holder).pharmacy_nameT.setText(tlt.pharmacy_name);
        ((medicine_gigRecyclerview.abcViewHolder) holder).medicine_sizeT.setText(tlt.medicine_size);
        ((medicine_gigRecyclerview.abcViewHolder) holder).priceT.setText(tlt.price);
        ((medicine_gigRecyclerview.abcViewHolder) holder).quantityT.setText(tlt.quantity);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class abcViewHolder extends RecyclerView.ViewHolder {
        ImageView ImgView;
        TextView medicine_nameT;
        TextView pharmacy_nameT;
        TextView medicine_sizeT;
        TextView priceT;
        TextView quantityT;

        public abcViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgView = (ImageView) itemView.findViewById(R.id.image);
            medicine_nameT = (TextView) itemView.findViewById(R.id.medicine_name);
            pharmacy_nameT = (TextView) itemView.findViewById(R.id.pharmacy_name);
            medicine_sizeT = (TextView) itemView.findViewById(R.id.medicine_size);
            priceT = (TextView) itemView.findViewById(R.id.price);
            quantityT = (TextView) itemView.findViewById(R.id.quantity);
        }
    }
}

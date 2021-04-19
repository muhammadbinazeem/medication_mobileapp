package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gigRecyclerView extends RecyclerView.Adapter  {

    private ArrayList<Doctor_gig> data;
    private ArrayList<medicine_gig> data1;
    boolean flag;

    public gigRecyclerView (ArrayList<Doctor_gig> data){
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gig,parent,false);
        return new abcViewHolder(view);
    }

    public Doctor_gig getItem(int position){
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Doctor_gig tlt = getItem(position);
        ((abcViewHolder) holder).doc_spec.setText(tlt.doctor_specalization);
        ((abcViewHolder) holder).title.setText(tlt.title);
        ((abcViewHolder) holder).Vname.setText(tlt.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class abcViewHolder extends RecyclerView.ViewHolder{
        ImageView ImgView;
        TextView doc_spec;
        TextView title;
        TextView Vname;
        public abcViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgView = (ImageView) itemView.findViewById(R.id.image);
            doc_spec = (TextView) itemView.findViewById(R.id.Name);
            title = (TextView) itemView.findViewById(R.id.description);
            Vname = (TextView) itemView.findViewById(R.id.VendorName);
        }
    }
}

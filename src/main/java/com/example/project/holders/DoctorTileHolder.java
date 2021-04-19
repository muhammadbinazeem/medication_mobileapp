package com.example.project.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Doctors_Gig;
import com.example.project.R;
import com.example.project.UserInfo;
import com.example.project.models.Doctor;
import com.example.project.models.Doctor_gig;

import java.io.Serializable;

import static java.lang.Integer.parseInt;

public class DoctorTileHolder extends RecyclerView.ViewHolder {
    public ImageView dp;
    public TextView name;
    public TextView title;
    public TextView specialization;
    public TextView rating;
    public  String desc;
    public UserInfo ui = new UserInfo();

    public DoctorTileHolder(@NonNull View itemView) {
        super(itemView);
        dp = (ImageView)itemView.findViewById(R.id.profileImage);
        title = (TextView)itemView.findViewById(R.id.title);
        name = (TextView)itemView.findViewById(R.id.name);
        specialization = (TextView)itemView.findViewById(R.id.specialization);
        rating = (TextView)itemView.findViewById(R.id.rating);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(dp.getContext(), name.getText(), Toast.LENGTH_LONG).show();
                Doctor_gig dc = new Doctor_gig(desc,specialization.getText().toString(),title.getText().toString(),name.getText().toString());
                Intent i = new Intent(dp.getContext(), Doctors_Gig.class);
                i.putExtra("Doctor", (Serializable) dc);
                i.putExtra("User", (Serializable)ui);
                dp.getContext().startActivity(i);
            }
        });
    }

    public TextView getName() {
        return name;
    }

    public TextView getSpecialization(){ return specialization; }

    public TextView getRating(){ return rating; }


}

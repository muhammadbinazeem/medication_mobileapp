package com.example.project.holders;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Doctors_List;
import com.example.project.R;
import com.example.project.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static androidx.core.content.ContextCompat.getSystemService;
import static java.security.AccessController.getContext;

public class TileHolder extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView hd;
    public TextView size;
    public TextView quantity;
    public TextView price;
    public TextView pharma;
    public ImageButton bo;
    public UserInfo ui = new UserInfo();


    public TileHolder(@NonNull View itemView) {
        super(itemView);
        img = (ImageView)itemView.findViewById(R.id.profileImage);
        hd = (TextView)itemView.findViewById(R.id.title);
        size = (TextView)itemView.findViewById(R.id.size);
        quantity = (TextView)itemView.findViewById(R.id.quantity);
        price = (TextView)itemView.findViewById(R.id.price);
        pharma = (TextView)itemView.findViewById(R.id.pharmaName);
        bo = (ImageButton)itemView.findViewById(R.id.bookBtn);
        bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(bo.getContext(),"this is notify",Toast.LENGTH_SHORT).show();
                NotificationManagerCompat notif= NotificationManagerCompat.from(bo.getContext());
                Notification not = new NotificationCompat
                        .Builder(bo.getContext(), com.example.project.holders.Base.CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.booking).setContentTitle("Medicine Booking: ").setContentText("Your medicine " + hd.getText().toString() + " have been booked. You can collect your order from your nearest branch of "+ R.id.pharmaName +".").build();
                notif.notify(1,not);


                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference().child("Medicine_orders");

                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("medicine_name", hd.getText().toString());
                userMap.put("user", ui.getName().toString());
                root.push().setValue(userMap);

            }
        });
    }

    public ImageView getImageView() {
        return img;
    }

    public TextView getTextView(){
        return hd;
    }

}

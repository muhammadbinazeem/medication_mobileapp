package com.example.project;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInfo implements  java.io.Serializable {

    String Email;
    String Password;
    String Name;
    String Type;

    public UserInfo() {
    }

    public void setter(UserInfo us) {
        this.Email = us.Email;
        this.Password = us.Password;
        this.Name = us.Name;
        this.Type = us.Type;
    }

    UserInfo(String email, String password, String name, String Type){
        this.Email = email;
        this.Password = password;
        this.Name = name;
        this.Type = Type;
    }

    void StoreData() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("User");

        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("Email", this.Email);
        userMap.put("Name", this.Name);
        userMap.put("Type", this.Type);
        userMap.put("Password", this.Password);

        root.push().setValue(userMap);
    }

    public ArrayList<UserInfo> RetreiveData() {
        final String[] EmailTemp = {null};
        final String[] NameTemp = {null};
        final String[] TypeTemp = {null};
        final String[] PasswordTemp = {null};

        final ArrayList <UserInfo> temp = new ArrayList<UserInfo>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapsho1t : snapshot.getChildren()) {

                    EmailTemp[0] =  snapsho1t.child("Email").getValue().toString();
                    NameTemp[0] = snapsho1t.child("Name").getValue().toString();
                    TypeTemp[0] = snapsho1t.child("Type").getValue().toString();
                    PasswordTemp[0] = snapsho1t.child("Password").getValue().toString();

                    UserInfo add = new UserInfo(EmailTemp[0], NameTemp[0], TypeTemp[0], PasswordTemp[0]);
                    temp.add(add);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return temp;
    }

    public String verifyUser(String EmailT, String TypeT, String PasswordT, ArrayList<UserInfo> temp) {
        for(UserInfo a:temp) {
            String b = a.Email;
            if (a.Email.equals(EmailT)) {
                if (a.Type.equals(TypeT) &&a.Password .equals(PasswordT)){
                    return a.Name;
                }
            }
        }
        return "";
    }

    public String getName() {
        return Name;
    }
}

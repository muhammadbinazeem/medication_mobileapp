package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    final ArrayList <UserInfo> temp = new ArrayList<UserInfo>();
    final String[] EmailTemp = {null};
    final String[] NameTemp = {null};
    final String[] TypeTemp = {null};
    final String[] PasswordTemp = {null};
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapsho1t : snapshot.getChildren()) {

                    EmailTemp[0] =  snapsho1t.child("Email").getValue().toString();
                    NameTemp[0] = snapsho1t.child("Name").getValue().toString();
                    TypeTemp[0] = snapsho1t.child("Type").getValue().toString();
                    PasswordTemp[0] = snapsho1t.child("Password").getValue().toString();

                    UserInfo add = new UserInfo(EmailTemp[0], PasswordTemp[0], NameTemp[0], TypeTemp[0]);
                    temp.add(add);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sign_up_activity();
    }

    private void sign_up_activity() {
        TextView dnot_have_account = findViewById(R.id.signUp);
        dnot_have_account.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void sign_in_button_click() {
        UserInfo temp2 = new UserInfo("mba@gmail.com", "mba123", "MbinAzeem", "Public User");
        EditText EmailAddress = findViewById(R.id.EmailAddress);
        Spinner dropStatus2 = findViewById(R.id.dropStatus2);
        EditText Passwordtemp = findViewById(R.id.Passwordtemp);

        String Email = EmailAddress.getText().toString();
        String Type = dropStatus2.getSelectedItem().toString();
        String Password = Passwordtemp.getText().toString();
        String NameT = "";
        for (UserInfo a:temp) {
            String b = a.Email;
            if (a.Email.equals(Email)) {
                b = a.Password;
                b = a.Type;
                if (a.Type.equals(Type) && a.Password.equals(Password)){
                    NameT = a.Name;
                    user = new UserInfo(a.Email, a.Password,a.Name, a.Type);
                }
            }
        }
        if (!NameT.equals("")) {
            if (!user.Type.equals("Public User")) {
                Intent intent = new Intent(this, after_signin.class);
                intent.putExtra("User",user);
                startActivityForResult(intent, REQUEST_CODE);
            }
            else {
                Intent intent = new Intent(this, ServiceMenu.class);
                intent.putExtra("User",user);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else {
            Toast.makeText(this, "Invalid Credentails", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.println(Log.DEBUG, "result **", "result");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            setContentView(R.layout.activity_main);
        }
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.button) {
            sign_in_button_click();
        };
    }

}
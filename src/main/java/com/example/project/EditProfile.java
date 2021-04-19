package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    UserInfo info = new UserInfo();
    TextView name1;
    UserInfo user;

    //private FirebaseDatabase db = FirebaseDatabase.getInstance();
    //private DatabaseReference root = db.getReference().child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        Intent a = getIntent();
        user = (UserInfo) a.getSerializableExtra("User");
        name1 = findViewById(R.id.Name1);
        set();
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.button2) {
            editProfile();
        };
    }

    private void set() {
        EditText UserName = findViewById(R.id.UserName);
        EditText Password = findViewById(R.id.Passwrd);
        EditText Email = findViewById(R.id.Email);

        name1.setText(user.Name);
        UserName.setHint(user.Name);
        Password.setHint(user.Password);
        Email.setHint(user.Email);

        UserName.setFocusable(false);
        Password.setFocusable(false);
        Email.setFocusable(false);
    }

    private void editProfile() {
        Button b = findViewById(R.id.button2);
        String buttonText = b.getText().toString();

        if (buttonText.equals("EditProfile")) {
            EditText UserName = findViewById(R.id.UserName);
            EditText Password = findViewById(R.id.Passwrd);
            EditText Email = findViewById(R.id.Email);

            b.setText("Save");

            UserName.setFocusableInTouchMode(true);
            Password.setFocusableInTouchMode(true);
            Email.setFocusableInTouchMode(true);

        } else if(buttonText.equals("Save")) {
            EditText UserName = findViewById(R.id.UserName);
            EditText Password = findViewById(R.id.Passwrd);
            EditText Email = findViewById(R.id.Email);
            TextView Type = findViewById(R.id.Type);

            info.Name = UserName.getText().toString();
            info.Type = Type.getText().toString();
            info.Password = Password.getText().toString();
            info.Email = Email.getText().toString();

            name1.setText(info.Name);
            UserName.setHint(info.Name);
            Password.setHint(info.Password);
            Email.setHint(info.Email);

            UserName.setFocusable(false);
            Password.setFocusable(false);
            Email.setFocusable(false);


            b.setText("EditProfile");
        }

    }
}

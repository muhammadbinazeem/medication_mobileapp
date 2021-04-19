package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    private void validateUser() {
        EditText Email = findViewById(R.id.EmailAddres);
        EditText Name = findViewById(R.id.Name1);
        Spinner dropStatus = findViewById(R.id.dropStatus);
        EditText Password = findViewById(R.id.Password);
        EditText RepeatPassword = findViewById(R.id.RepeatPassword);

        String Emailtemp = Email.getText().toString();
        String nametemp = Name.getText().toString();
        String Type = dropStatus.getSelectedItem().toString();
        String PssTemp = Password.getText().toString();

        if (!Password.getText().toString().equals(RepeatPassword.getText().toString())) {
            Toast.makeText(this, "Invalid Credentails", Toast.LENGTH_LONG).show();
        } else if (!Emailtemp.equals("") && !nametemp.equals("") && !PssTemp.equals("")) {
            if (Type.equals("Public User")) {
                UserInfo info = new UserInfo(Emailtemp, PssTemp, nametemp, Type);
                info.StoreData();

                Log.d("data ** ", Emailtemp + " " + nametemp + " " + Type + " " + PssTemp);

                Intent intent = new Intent(this, after_signin.class);
                intent.putExtra("User", info);
                startActivityForResult(intent, REQUEST_CODE);
            }
            else {
                //public user
            }
        } else {
            Toast.makeText(this, "Input Empty", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.button) {
            validateUser();
        };
    }


}

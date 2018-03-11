package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //final EditText etUserName = (EditText) findViewById(R.id.etlastname);
        //final EditText etdob = (EditText) findViewById(R.id.etpassword);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        //String dob = intent.getStringExtra("dob");

        String message = "Hello "+ firstName + " " + lastName + ", welcome to your user interface";
        welcomeMessage.setText(message);
        textView.setText(firstName + " " + lastName);
        //Toast.makeText(getApplicationContext(),dob, Toast.LENGTH_SHORT).show();
        //etdob.setText(dob);

    }
}

package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        // add the buttons that will be used to switch the pages on the home screen
        final Button profile = (Button) findViewById(R.id.button);
        final Button suggest = (Button) findViewById(R.id.button2);
        final Button counter = (Button) findViewById(R.id.button3);
        final Button info = (Button) findViewById(R.id.button4);



        //this welcomes the user after login
            //final EditText etUserName = (EditText) findViewById(R.id.etlastname);
            //final EditText etdob = (EditText) findViewById(R.id.etpassword);
            final TextView textView = (TextView) findViewById(R.id.textView);
            final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

            Intent intent = getIntent();
            String firstName = intent.getStringExtra("firstName");
            String lastName = intent.getStringExtra("lastName");

            final String email = intent.getStringExtra("email");
            //intent.putExtra("email",email);

            //String dob = intent.getStringExtra("dob");

            String message = "Hello " + firstName + " " + lastName + ", welcome to your user interface";
            welcomeMessage.setText(message);
            textView.setText(firstName + " " + lastName);
            //Toast.makeText(getApplicationContext(),dob, Toast.LENGTH_SHORT).show();
            //etdob.setText(dob);

        profile.setOnClickListener(new View.OnClickListener() { // look for click for registerLink and execute code below.
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(UserAreaActivity.this, ProfileActivity.class);
                UserAreaActivity.this.startActivity(profileIntent);
            }
        });

        suggest.setOnClickListener(new View.OnClickListener() { // look for click for registerLink and execute code below.
            @Override
            public void onClick(View v) {
                Intent suggestIntent = new Intent(UserAreaActivity.this, FoodSuggestActivity.class);
                suggestIntent.putExtra("email",email);
                UserAreaActivity.this.startActivity(suggestIntent);
            }
        });

        counter.setOnClickListener(new View.OnClickListener() { // look for click for registerLink and execute code below.
            @Override
            public void onClick(View v) {
                Intent counterIntent = new Intent(UserAreaActivity.this, MainActivity.class);
                UserAreaActivity.this.startActivity(counterIntent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() { // look for click for registerLink and execute code below.
            @Override
            public void onClick(View v) {
                Intent funFactIntent = new Intent(UserAreaActivity.this, FunFactActivity.class);
                UserAreaActivity.this.startActivity(funFactIntent);
            }
        });




    }
}

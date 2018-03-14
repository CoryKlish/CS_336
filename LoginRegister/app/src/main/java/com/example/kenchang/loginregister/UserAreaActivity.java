package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout; //For navigation menu
    private ActionBarDrawerToggle mToggle; //For navigation menu

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


        //for navigation bar:
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //works when any item on the action bar is tapped on
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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

            //this welcomes the user after login
            //final EditText etUserName = (EditText) findViewById(R.id.etlastname);
            //final EditText etdob = (EditText) findViewById(R.id.etpassword);
            final TextView textView = (TextView) findViewById(R.id.textView);
            final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

            Intent intent = getIntent();
            String firstName = intent.getStringExtra("firstName");
            String lastName = intent.getStringExtra("lastName");
            //String dob = intent.getStringExtra("dob");

            String message = "Hello " + firstName + " " + lastName + ", welcome to your user interface";
            welcomeMessage.setText(message);
            textView.setText(firstName + " " + lastName);
            //Toast.makeText(getApplicationContext(),dob, Toast.LENGTH_SHORT).show();
            //etdob.setText(dob);

        //for navigation bar:
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);

    }

    // Changes pages
    public void selectItemDrawer(MenuItem menuItem){
        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.nav_search:
                fragmentClass = SearchFoods.class;
                break;
            case R.id.nav_account:
                fragmentClass = MyAccount.class;
                break;
            case R.id.nav_suggestions:
                fragmentClass = FoodSuggestion.class;
                break;
            case R.id.nav_count:
                fragmentClass = NutritionCounter.class;
                break;
            case R.id.nav_fact:
                fragmentClass = FunFact.class;
                break;
            case R.id.nav_logout:
                fragmentClass = LoginActivity.class;
                break;
            default:
                fragmentClass = SearchFoods.class;
        }
        try {
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.f1content,myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    //this makes the hamburger button work when clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
//<user-permission android:name="android.permission.INTERNET"></user-permission>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);


        final EditText checkUsername = (EditText) findViewById(R.id.checkUsername);
        final EditText checkPassword = (EditText) findViewById(R.id.checkPassword);
        final EditText getAllergy = (EditText) findViewById(R.id.getAllergy);
        final Button addAllergy = (Button) findViewById(R.id.addAllergy);
        final Button removeAllergy = (Button) findViewById(R.id.removeAllergy);

        //Add allergy to the database
        addAllergy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String username = checkUsername.getText().toString();
                final String password = checkPassword.getText().toString();
                final String allergy = getAllergy.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {

                                Toast.makeText(getApplicationContext(),"Allergy Successfully Added", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(ProfileActivity.this, UserAreaActivity.class);
                                ProfileActivity.this.startActivity(intent);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                                builder.setMessage("Incorrect Password")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                };


                ProfileAddRequest profileAddRequest = new ProfileAddRequest(username,password,allergy,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(profileAddRequest);

            }
        });


        //Remove allergy to the database (Update)
        removeAllergy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String username = checkUsername.getText().toString();
                final String password = checkPassword.getText().toString();
                final String allergy = getAllergy.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            System.out.print(response);
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {

                                Toast.makeText(getApplicationContext(),"Allergy Successfully Removed", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(ProfileActivity.this, UserAreaActivity.class);
                                ProfileActivity.this.startActivity(intent);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                                String msg = jsonResponse.getString("message");
                                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();


/*                                builder.setMessage("Incorrect Password")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();*/
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                };


                ProfileRemoveRequest profileRemoveRequest = new ProfileRemoveRequest(username,password,allergy,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(profileRemoveRequest);

            }
        });

    }
}

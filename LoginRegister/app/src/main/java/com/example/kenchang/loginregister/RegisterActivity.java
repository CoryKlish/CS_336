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

public class RegisterActivity extends AppCompatActivity {
//<user-permission android:name="android.permission.INTERNET"></user-permission>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etpassword = (EditText) findViewById(R.id.etpassword);
        final EditText etFirstName = (EditText) findViewById(R.id.etfirstName);
        final EditText etlastName = (EditText) findViewById(R.id.etlastname);
        final EditText etemail = (EditText) findViewById(R.id.etemail);
        final EditText etdob = (EditText) findViewById(R.id.etdob);
        final Button bRegister = (Button) findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String firstName = etFirstName.getText().toString();
                final String lastName = etlastName.getText().toString();
                final String email = etemail.getText().toString();
                final String password = etpassword.getText().toString();
                //final int password = Integer.parseInt(etpassword.getText().toString());
                final String dob = etdob.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(),"Registration Successful, continue to login", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                            //Toast.makeText(getApplicationContext(),"It passed", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e){
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"It didn't pass", Toast.LENGTH_SHORT).show();
                        }


                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(firstName,lastName,password,email,dob,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

    }
}

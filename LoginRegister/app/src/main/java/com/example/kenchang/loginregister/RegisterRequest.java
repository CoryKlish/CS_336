package com.example.kenchang.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterRequest extends StringRequest{ // request to register.php file on the server and get a response as a string

    private static final String REGISTER_REQUEST_URL = "http://ec2-18-219-194-148.us-east-2.compute.amazonaws.com/registerV2.php";
    private Map<String,String> params;

    public RegisterRequest(String firstName, String lastName, String password, String email, String dob, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);
        params.put("password", password);
        params.put("dob", dob);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }




}

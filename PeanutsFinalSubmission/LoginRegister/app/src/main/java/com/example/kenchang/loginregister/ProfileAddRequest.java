package com.example.kenchang.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ProfileAddRequest extends StringRequest{ // request to register.php file on the server and get a response as a string

    //change this
    private static final String REGISTER_REQUEST_URL = "http://ec2-18-219-194-148.us-east-2.compute.amazonaws.com/addAllergy.php";
    private Map<String,String> params;

    public ProfileAddRequest(String username, String password, String allergy, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("allergy", allergy);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}


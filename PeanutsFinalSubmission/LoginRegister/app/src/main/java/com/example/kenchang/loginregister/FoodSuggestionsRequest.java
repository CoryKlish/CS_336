package com.example.kenchang.loginregister;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class FoodSuggestionsRequest extends StringRequest{
    //simTest.php
    private static final String Food_Suggestions_URL = "http://ec2-18-219-194-148.us-east-2.compute.amazonaws.com/simFood.php";
    private Map<String,String> params;

    public FoodSuggestionsRequest(String user, String foodSearch, Response.Listener<String> listener) {
        super(Request.Method.POST, Food_Suggestions_URL, listener, null);
        params = new HashMap<>();
        params.put("username", user);
        params.put("food",foodSearch);

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }



}

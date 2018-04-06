package com.example.compsci.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

public class foodRequests extends StringRequest{
    private static final String food_URL="http://ec2-18-219-194-148.us-east-2.compute.amazonaws.com/queryFood.php"; //gets url database
    private Map<String, String> parameter;

    public foodRequests(String foodName, Response.Listener<String> listener){ //all these will pass to constructor when the instance is created
        super(Request.Method.POST, food_URL, listener,null); //execute the requests (method post is send some data to php and it is going to respond with some data
        parameter= new HashMap<>();
        parameter.put("name",foodName); //all data are in paramemters
    }

    @Override
    public Map<String, String> getParams() { // volley accesses this data
        return parameter;
    }
}

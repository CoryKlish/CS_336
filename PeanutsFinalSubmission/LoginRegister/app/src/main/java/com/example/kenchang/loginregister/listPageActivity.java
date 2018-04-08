package com.example.kenchang.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;

public class listPageActivity extends AppCompatActivity {
    String response;
    JSONObject object;
    JSONArray array;
    listDataAdapter adapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = (ListView) findViewById(R.id.List_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (listPageActivity.this, MainActivity.class);
                listPageActivity.this.startActivity(intent);
            }
        });
        adapter = new listDataAdapter (this, R.layout.row_layout); //create object of row layout
        list.setAdapter(adapter);   //list view will take row layout thats in the adapter
        response = getIntent().getExtras().getString("jsonData"); // get data from intent
        //pass json and display it in a listview
        try {
            object = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
            //bject = new JSONObject(response); //initialize json object and pass json string
            array = object.getJSONArray("foodList"); //intialize json array
            int count=1;
            String name, energy, fiber, sugar, protein, carbs, fats; //these are info available on json data
            //get each row from json data

            while(count < array.length()){ //traverse length of json array
                JSONObject jsonResponse= array.getJSONObject(count); //return json object on the row
                name=jsonResponse.getString("productName");
                energy=jsonResponse.getString("calories");
                fiber=jsonResponse.getString("fiber");
                sugar=jsonResponse.getString("sugar");
                protein=jsonResponse.getString("protein");
                carbs=jsonResponse.getString("carbs");
                fats=jsonResponse.getString("fat");
                //save all info as object of datamaker class
                dataMaker maker= new dataMaker(name, energy, fiber, sugar, protein, carbs, fats);
                adapter.add(maker); //pass all objects into the adapter
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

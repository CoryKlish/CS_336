package com.example.kenchang.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class FoodSuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_suggest);

        final EditText foodSearch = (EditText) findViewById(R.id.etFoodSearch);
        final Button search = (Button) findViewById(R.id.bSearch);
        final TextView suggest1 = (TextView) findViewById(R.id.tvSuggest1);
        /*final TextView suggest2 = (TextView) findViewById(R.id.tvSuggest2);
        final TextView suggest3 = (TextView) findViewById(R.id.tvSuggest3);
        final TextView suggest4 = (TextView) findViewById(R.id.tvSuggest4);
        final TextView suggest5 = (TextView) findViewById(R.id.tvSuggest5);*/

        search.setOnClickListener(new View.OnClickListener(){
            //final String lol = foodSearch.getText().toString();
            public void onClick(View v){
                Intent searchIntent = new Intent(FoodSuggestActivity.this, FoodSuggestActivity.class);

                final String foodPass = foodSearch.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {



                            //System.out.println("HELLLLLLOOOO");
                            //JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            System.out.println(response);
                            JSONArray array = jsonResponse.getJSONArray("result");
                            JSONObject smallArray = array.getJSONObject(0); // get first position of the array (success or not)
                            boolean success = smallArray.getBoolean("success");
                            if(success) {
                                //TextView [] answer = {suggest1, suggest2, suggest3, suggest4, suggest5};
                                String rstName = "";
                                double rstPercentage = 0;
                                String total = "";
                                for(int cnt = 1; cnt < array.length(); cnt++){
                                    smallArray = array.getJSONObject(cnt);
                                    rstName = smallArray.getString("productName");
                                    rstName = rstName.substring(0,rstName.indexOf("UPC")-1);
                                    DecimalFormat trim = new DecimalFormat("#.###");
                                    rstPercentage = Double.parseDouble(smallArray.getString("matchPct"))*100;
                                    rstPercentage = Double.parseDouble(trim.format(rstPercentage));
                                    //answer[cnt-1].setText(rstName + " " + rstPercentage + "%");
                                    total = total + cnt + ". " + rstName + " " + rstPercentage + "%\n\n";
                                }
                                suggest1.setText(total);




                /*
                                //get allergies into a String
                                smallArray = array.getJSONObject(1);
                                JSONArray allergiesArray = smallArray.getJSONArray("allergies");

                                    //JSONObject allergies = array.getJSONObject(1);
                                    //JSONArray allergiesArray = allergies.getJSONArray("allergies");


                                JSONObject allergy;
                                String allergyList = "";
                                for(int cnt = 0; cnt < allergiesArray.length(); cnt++) {
                                    allergy = allergiesArray.getJSONObject(cnt);
                                    if(cnt + 1 == allergiesArray.length())
                                        allergyList += allergy.getString("allergy");
                                    else
                                        allergyList += allergy.getString("allergy") + "| ";
                                }
                                System.out.println("Allergy List is --> " + allergyList);


                                //get ingredients from searched food
                                smallArray = array.getJSONObject(2);
                                String foodSearchIngred = smallArray.getString("ingredients");

                                System.out.println("User searched: " + foodPass + " Ingredients: " + foodSearchIngred);

                                // find # of allergy ingredients in search food
                                Helper h = new Helper();
                                int [] info = h.similarities(foodSearchIngred,allergyList);
                                int allergyInSearchedFood = info[2];

                                System.out.println("Allergy Matched in Searched Food --> " + allergyInSearchedFood);

                                //start calculating similaries with every food (use insertion sort)
                                //double [] topFive = new double[5];
                                String matchFirstName = "";
                                String matchFirstIngred = "";

                                String testerName = "";
                                String testerIngred = "";

                                int leftOver = 0;
                                double accuracy = 0;

                                for(int cnt = 3; cnt < array.length(); cnt++){
                                    smallArray = array.getJSONObject(cnt);
                                    testerName = smallArray.getString("productName");
                                    testerIngred = smallArray.getString("ingredients");
                                    info = h.similarities(foodSearchIngred,testerIngred);
                                    leftOver = info[3] - 1;
                                    accuracy = (double)(info[2]*2)/(double)(info[0]+info[1]-allergyInSearchedFood);
                                    System.out.println(testerName + " Left Over: " + leftOver + " Accuracy: " + accuracy);
                                }



                                    //Intent intent = new Intent(FoodSuggestActivity.this, FoodSuggestActivity.class); // check this
                                    //FoodSuggestActivity.this.startActivity(intent);

                */
                                Toast.makeText(getApplicationContext(),"Searching Successful", Toast.LENGTH_LONG).show();

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FoodSuggestActivity.this);
                                builder.setMessage("Searching Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                };

                FoodSuggestionsRequest foodSuggestionrequest = new FoodSuggestionsRequest("abc@yahoo.com",foodPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(FoodSuggestActivity.this);
                queue.add(foodSuggestionrequest);


            }


        });


/*
        final EditText etFoodSearch = (EditText) findViewById(R.id.etFoodSearch);
        final EditText s1 = (EditText) findViewById(R.id.suggestion1);
        final EditText s2 = (EditText) findViewById(R.id.suggestion2);
        final EditText s3 = (EditText) findViewById(R.id.suggestion3);
        final EditText s4 = (EditText) findViewById(R.id.suggestion4);
        final EditText s5 = (EditText) findViewById(R.id.suggestion5);
        final Button bSearch = (Button) findViewById(R.id.search);

        bSearch.setOnClickListener(new View.OnClickListener() { // look for click for registerLink and execute code below.

            final String foodSearch = etFoodSearch.getText().toString();

            public void onClick(View v){
                Intent searchIntent = new Intent(FoodSuggestActivity.this, FoodSuggestActivity.class);

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {

                                // loop to get food and compare
                                String rst1 = "OMG";
                                String rst2 = "This";
                                String rst3 = "actually";
                                String rst4 = "worked";
                                String rst5 = "!";


                                String firstName = jsonResponse.getString("food");
                                String lastName = jsonResponse.getString("lastName");
                                String dob = jsonResponse.getString("dob");

                                s1.setText(rst1);
                                s2.setText(rst2);
                                s3.setText(rst3);
                                s4.setText(rst4);
                                s5.setText(rst5);

                                Toast.makeText(getApplicationContext(),"Searching Successful", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(FoodSuggestActivity.this, FoodSuggestActivity.class); // check this
                                FoodSuggestActivity.this.startActivity(intent);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FoodSuggestActivity.this);
                                builder.setMessage("Searching Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                };

                FoodSuggestionsRequest Food_Suggestions_URL = new FoodSuggestionsRequest(foodSearch,responseListener);
                RequestQueue queue = Volley.newRequestQueue(FoodSuggestActivity.this);
                queue.add(Food_Suggestions_URL);


            }

        });
    */


    }
}

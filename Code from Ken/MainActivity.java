package com.example.compsci.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.foodname);
        final Button add = (Button) findViewById(R.id.adding);
        final TextView cal = (TextView) findViewById(R.id.cal);
        final TextView pro = (TextView) findViewById(R.id.pro);
        final TextView carbs = (TextView) findViewById(R.id.carbs);
        final TextView fiber = (TextView) findViewById(R.id.fiber);
        final TextView sugars = (TextView) findViewById(R.id.sug);
        final TextView fats = (TextView) findViewById(R.id.fat);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String food = name.getText().toString();
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //anything happens when the response has been executed, parameter is response from php
                        try {
                            JSONObject Res = new JSONObject(response.substring(response.indexOf("[") + 1, response.lastIndexOf("}") + 1));
                            boolean success = Res.getBoolean("success");
                            if (success) { //if successful it will take the list i plan to make listpageactivity
                                Intent intent = new Intent(MainActivity.this, listPageActivity.class);
                                intent.putExtra("jsonData", response); //attach json string into intent object
                                MainActivity.this.startActivity(intent); //start new activity
                            } else {  //shows enter again and retry when there are no foods listed
                                AlertDialog.Builder error = new AlertDialog.Builder(MainActivity.this);
                                error.setMessage("Enter Again")
                                        .setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace(); // if something goes wrong object variable then it will catch the error and print it out

                        }
                    }
                };
                foodRequests requests = new foodRequests(food, listener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(requests);
            }
        });
        int result,res2, res3,res4,res5,res6;
        String string="";
        String re2= "";
        String re3="";
        String re4="";
        String re5="";
        String re6="";
        String calories= dataMaker.getEnergy();
        String carb= dataMaker.getCarbs();
        String fat= dataMaker.getFat();
        String fib= dataMaker.getFiber();
        String prot= dataMaker.getProtein();
        String sug= dataMaker.getSugars();
        array.getCalories().add(calories);
        array.getCarbs().add(carb);
        array.getFiber().add(fib);
        array.getProtein().add(prot);
        array.getFat().add(fat);
        array.getSugar().add(sug);
        if (calories!=null){
            cal.setText(dataMaker.getEnergy());
            pro.setText(dataMaker.getProtein());
            carbs.setText(dataMaker.getCarbs());
            fiber.setText(dataMaker.getFiber());
            sugars.setText(dataMaker.getSugars());
            fats.setText(dataMaker.getFat());
            if (array.getCalories().size()>2 ) {
                result= (int) Double.parseDouble(calories);
                res2= (int) Double.parseDouble(carb);
                res3= (int) Double.parseDouble(fat);
                res4= (int) Double.parseDouble(fib);
                res5= (int) Double.parseDouble(prot);
                res6= (int) Double.parseDouble(sug);
                int count=2;
                while(count<array.getCalories().size()) {
                    double b = Double.parseDouble(array.getCalories().get(array.getCalories().size() - count));
                    result = (int) (result + b);
                    string = result + "";
                    double c = Double.parseDouble(array.getSugar().get(array.getSugar().size() - count));
                    res2 = (int) (res2 + c);
                    re2 = res2 + "";
                    double d = Double.parseDouble(array.getCarbs().get(array.getCarbs().size() - count));
                    res3 = (int) (res3 + d);
                    re3 = res3 + "";
                    double e = Double.parseDouble(array.getFiber().get(array.getFiber().size() - count));
                    res4 = (int) (res4 + e);
                    re4 = res4 + "";
                    double f = Double.parseDouble(array.getFat().get(array.getFat().size() - count));
                    res5 = (int) (res5 + f);
                    re5 = res5 + "";
                    double g = Double.parseDouble(array.getProtein().get(array.getProtein().size() - count));
                    res6 = (int) (res6 + g);
                    re6 = res6 + "";
                    count = count+1;
                }
                cal.setText(string);
                pro.setText(re6);
                carbs.setText(re3);
                fiber.setText(re4);
                sugars.setText(re2);
                fats.setText(re5);
            }
        }
    }
}

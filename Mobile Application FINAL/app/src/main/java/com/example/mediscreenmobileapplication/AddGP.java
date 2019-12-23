package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddGP extends AppCompatActivity {

    private String email;

    EditText emailEditText;

    Button searchGPButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gp);

        emailEditText = findViewById(R.id.emailEditText);

        searchGPButton = findViewById(R.id.searchGPButton);

        searchGPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserInput();

                if (validate()){

                    searchForGP();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No GP's with that email are\n" +
                            "registered with JAGI", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void searchForGP(){

        String URL = "http://192.168.43.216/Mobile_Application_Backend/searchGPs.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("HMMMM" + response);

                // System.err:  org.json.JSONException: Value dbConnect.php of type java.lang.String cannot be converted to JSONObject
                // Can't figure out how to fix this but the data is stored in database successfully
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    //--------------------- 1 OR "1" ?-------------------
                    if(success.equals("1")){

                        System.out.println("RESPONSE 1 - " + response);
                        //-------  SEND EMAIL TO GP WITH INVITE TO CONNECT ------

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                    else{
                        System.out.println("RESPONSE 2 - " + response);

                        Toast.makeText(getApplicationContext(), "Email and Password didn't match", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                    System.out.println("RESPONSE 3 - " + response);
                }
                Toast.makeText(getApplicationContext(), "SERVER RESPONSE: " + response, Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();

                params.put("email", email);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void getUserInput(){

        // Get UI components
        emailEditText = findViewById(R.id.emailEditText);

        // Get the values of the user input
        email = emailEditText.getText().toString();
    }

    public boolean validate(){

        boolean valid = true;

        if (email.isEmpty() || email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+/.[a-z]{2,4}$")){
            emailEditText.setError("Please enter a valid email address");
            valid = false;
        }
        return valid;
    }
}

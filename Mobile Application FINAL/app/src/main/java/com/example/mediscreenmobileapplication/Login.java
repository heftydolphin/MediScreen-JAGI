package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    User user;

    private String email;
    private String password;

    EditText emailEditText;
    EditText passwordEditText;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {

            finish();
            startActivity(new Intent(this, UserHome.class));
        }

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserInput();

                if (validate()){

                   // user.setLoggedIn();

                    loginUser();
                }
                else{
                    Toast.makeText(getApplicationContext(), "yugssyuguyu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loginUser(){

        String URL = "http://192.168.43.216/Mobile_Application_Backend/loginUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "ERROR: Registration failed  " + response, Toast.LENGTH_LONG).show();
                System.out.println("RESPONSE" + response);

                // System.err:  org.json.JSONException: Value dbConnect.php of type java.lang.String cannot be converted to JSONObject
                // Can't figure out how to fix this but the data is stored in database successfully
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    //--------------------- 1 OR "1" ?-------------------
                    if(success.equals("1")){

                        System.out.println("RESPONSE" + response);
                        //getting the user from the response
                        JSONObject userJson = jsonObject.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getString("email"),
                                userJson.getString("password"),
                                userJson.getString("fName"),
                                userJson.getString("lName"),
                                userJson.getString("memberNum"),
                                userJson.getString("policyNum"));

                        System.out.println(user.toString());

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        startActivity(new Intent(getApplicationContext(), UserHome.class));

                        //Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_LONG).show();
                    }
                    else{
                        System.out.println("RESPONSeeeE" + response);


                        Toast.makeText(getApplicationContext(), "Email and Password didn't match", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                    System.out.println("RESPONSE" + response);
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
                System.out.println("HELLLLLL");

                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    public void getUserInput(){

        // Get UI components
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Get the values of the user input
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
    }

    public boolean validate(){

        boolean valid = true;

        if (email.isEmpty() || email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+/.[a-z]{2,4}$")){
            emailEditText.setError("Please enter a valid email address");
            valid = false;
        }
        if (password.isEmpty() || password.matches("(?=.*//d)(?=.*[a-z])(?=.*[A-Z]).{8,}")){
            passwordEditText.setError("Password must contain: One number, One uppercase, lowercase letter and at least 8 characters");
            valid = false;
        }

        return valid;
    }
}


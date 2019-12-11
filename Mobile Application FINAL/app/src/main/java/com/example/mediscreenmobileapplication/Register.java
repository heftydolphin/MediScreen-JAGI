package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Register extends AppCompatActivity {

    User user;

    public static boolean loggedIn;

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String memberNumber;
    private String policyNumber;

    EditText emailEditText;
    EditText passwordEditText;
    EditText fNameEditText;
    EditText lNameEditText;
    EditText memberNumberEditText;
    EditText policyNumberEditText;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserInput();


                if (validate()){

                    user = new User(email, password, fName, lName, memberNumber, policyNumber);

                    registerUser();
                }
                else{
                    Toast.makeText(getApplicationContext(), "yugssyuguyu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registerUser(){

        String URL = "http://192.168.43.216/Mobile_Application_Backend/registerUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //System.out.println("RESPONSE" + response);

                // System.err:  org.json.JSONException: Value dbConnect.php of type java.lang.String cannot be converted to JSONObject
                // Can't figure out how to fix this but the data is stored in database successfully
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    //--------------------- 1 OR "1" ?-------------------
                    if(success.equals("1")){

                        Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                    else{

                        Toast.makeText(getApplicationContext(), "ERROR: Registration failed  ", Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("password", password);
                params.put("fName", fName);
                params.put("lName", lName);
                params.put("insuranceMemberNum", memberNumber);
                params.put("insurancePolicyNum", policyNumber);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        Intent loginActivity = new Intent(getApplicationContext(), Login.class);
        startActivity(loginActivity);
    }

    public void getUserInput(){

        // Get UI components
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        fNameEditText = findViewById(R.id.fNameEditText);
        lNameEditText = findViewById(R.id.lNameEditText);
        memberNumberEditText = findViewById(R.id.memberNumberEditText);
        policyNumberEditText = findViewById(R.id.policyNumberEditText);

        // Get the values of the user input
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        fName = fNameEditText.getText().toString();
        lName = lNameEditText.getText().toString();
        memberNumber = memberNumberEditText.getText().toString();
        policyNumber = policyNumberEditText.getText().toString();
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
        if (fName.isEmpty() || fName.matches("[A-Za-z]{2}")){
            fNameEditText.setError("Please enter a valid name");
            valid = false;
        }
        if (lName.isEmpty() || lName.matches("[A-Za-z '-]")){
            lNameEditText.setError("Please enter a valid name");
            valid = false;
        }
        if (memberNumber.isEmpty() || memberNumber.matches("[0-9]")){
            memberNumberEditText.setError("Please enter a valid insurance member number");
            valid = false;
        }
        if (policyNumber.isEmpty() || policyNumber.matches("[0-9]")){
            policyNumberEditText.setError("Please enter a valid insurance policy number");
            valid = false;
        }
        return valid;
    }
}

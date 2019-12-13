package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class UserDetails extends AppCompatActivity {

    User user;

    EditText emailEditText;
    EditText passwordEditText;
    EditText fNameEditText;
    EditText lNameEditText;
    EditText memberNumberEditText;
    EditText policyNumberEditText;

    private String emailHolder;

    private String email;
    private String password;
    private String fName;
    private String lName;
    private String memberNumber;
    private String policyNumber;

    Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        user = SharedPrefManager.getInstance(this).getUser();
        emailHolder = user.getEmail();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        fNameEditText = findViewById(R.id.fNameEditText);
        lNameEditText = findViewById(R.id.lNameEditText);
        memberNumberEditText = findViewById(R.id.memberNumberEditText);
        policyNumberEditText = findViewById(R.id.policyNumberEditText);

        populateFields();

        saveChangesButton = findViewById(R.id.saveChangesButton);

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                fName = fNameEditText.getText().toString();
                lName = lNameEditText.getText().toString();
                memberNumber = memberNumberEditText.getText().toString();
                policyNumber = policyNumberEditText.getText().toString();

                updateUser();
            }
        });
    }

    private void updateUser() {

        String URL = "http://192.168.43.216/Mobile_Application_Backend/updateUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "ERROR: Update failed  " + response, Toast.LENGTH_LONG).show();
                System.out.println("RESPONSE" + response);

                // System.err:  org.json.JSONException: Value dbConnect.php of type java.lang.String cannot be converted to JSONObject
                // Can't figure out how to fix this but the data is stored in database successfully
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    //--------------------- 1 OR "1" ?-------------------
                    if(success.equals("1")){

                        SharedPrefManager.getInstance(getApplicationContext()).logout();

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

                        Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_LONG).show();
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


                System.out.println("gggggg" );

                Toast.makeText(getApplicationContext(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("emailHolder", emailHolder);
                params.put("email", email);
                params.put("password", password);
                params.put("fName", fName);
                params.put("lName", lName);
                params.put("memberNumber", memberNumber);
                params.put("policyNumber", policyNumber);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void populateFields(){

        emailEditText.setText(user.getEmail());
        fNameEditText.setText(user.getfName());
        lNameEditText.setText(user.getlName());
        memberNumberEditText.setText(user.getMemberNumber());
        policyNumberEditText.setText(user.getPolicyNumber());
    }
}

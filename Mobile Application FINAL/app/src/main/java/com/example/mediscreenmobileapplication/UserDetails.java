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

    private Button saveChangesButton;
    private Button deleteAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        user = SharedPrefManager.getInstance(this).getUser();
        emailHolder = user.getEmail();
        System.out.println(emailHolder);

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

        deleteAccountButton = findViewById(R.id.deleteAccountButton);

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popUp();
            }
        });
    }

    private void deleteUser(){

        String URL = "http://192.168.43.216/Mobile_Application_Backend/deleteUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "ERROR: Delete failed  " + response, Toast.LENGTH_LONG).show();
                System.out.println("RESPONSE" + response);

                // System.err:  org.json.JSONException: Value dbConnect.php of type java.lang.String cannot be converted to JSONObject
                // Can't figure out how to fix this but the data is stored in database successfully
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    String success = jsonObject.getString("success");

                    //--------------------- 1 OR "1" ?-------------------
                    if(success.equals("1")){

                        System.out.println("RESPONSE" + response);

                        SharedPrefManager.getInstance(getApplicationContext()).logout();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        Toast.makeText(getApplicationContext(), "ACCOUNT DELETED PERMANENTLY", Toast.LENGTH_LONG).show();

                    }
                    else{
                        System.out.println("RESPONSE 2 - " + response);


                        Toast.makeText(getApplicationContext(), "User could not be found in database", Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() {

                user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                String email = user.getEmail();


                Map<String, String> params = new HashMap<>();

                System.out.println("Send parameter to backend" + email);

                params.put("email", email);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void popUp(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("DELETE ACCOUNT PERMANENTLY");
        builder.setMessage("Are you sure you would like to delete your account from the JAGI Application?\n" +
                "There will be no way to recover your account if you proceed.");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                System.out.println("Send parameters to backend");

                params.put("email", email);
                params.put("password", password);
                params.put("fName", fName);
                params.put("lName", lName);
                params.put("memberNum", memberNumber);
                params.put("policyNum", policyNumber);
                params.put("emailHolder", emailHolder);

                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public void populateFields(){

        emailEditText.setText(user.getEmail());
        passwordEditText.setText(user.getPassword());
        fNameEditText.setText(user.getfName());
        lNameEditText.setText(user.getlName());
        memberNumberEditText.setText(user.getMemberNumber());
        policyNumberEditText.setText(user.getPolicyNumber());
    }
}

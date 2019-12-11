package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class MedicalHistory extends AppCompatActivity {

    User user;

    private String emailHolder;

    private String gender;
    private String age;
    private String exercise;
    private String bloodPressure;
    private String pastConditions;
    private String glucose;
    private String familyHistory;
    private String cholesterol;
    private String height;
    private String weight;

    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner exerciseSpinner;
    Spinner bloodPressureSpinner;
    Spinner pastConditionsSpinner;
    Spinner glucoseSpinner;
    Spinner familyHistorySpinner;
    Spinner cholesterolSpinner;

    EditText heightEditText;
    EditText weightEditText;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        user = SharedPrefManager.getInstance(this).getUser();
        emailHolder = user.getEmail();

        getSpinnerObjects();

        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);

        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserInput();

                if (validate()){

                    saveMedicalHistory();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid user input", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveMedicalHistory() {

        String URL = "http://192.168.43.216/Mobile_Application_Backend/saveMedicalHistory.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("SAVE MEDICAL HISTORY - RESPONSE" + response);

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
                    System.out.println("JSON EXCEPTION - RESPONSE" + response);
                }
                Toast.makeText(getApplicationContext(), "SERVER RESPONSE: " + response, Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "NO RESPONSE FROM DATABASE: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();

                params.put("gender", gender);
                params.put("age", age);
                params.put("exercise", exercise);
                params.put("bloodPressure", bloodPressure);
                params.put("pastConditions", pastConditions);
                params.put("glucose", glucose);
                params.put("familyHistory", familyHistory);
                params.put("cholesterol", cholesterol);
                params.put("height", height);
                params.put("weight", weight);
                params.put("patientID", emailHolder);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

//        Intent UserHome = new Intent(getApplicationContext(), UserHome.class);
//        startActivity(UserHome);
    }

    public void getUserInput(){

        // Get the values of the user input
        gender = genderSpinner.getSelectedItem().toString();
        age = ageSpinner.getSelectedItem().toString();
        exercise = exerciseSpinner.getSelectedItem().toString();
        bloodPressure = bloodPressureSpinner.getSelectedItem().toString();
        pastConditions = pastConditionsSpinner.getSelectedItem().toString();
        glucose = glucoseSpinner.getSelectedItem().toString();
        familyHistory = familyHistorySpinner.getSelectedItem().toString();
        cholesterol = cholesterolSpinner.getSelectedItem().toString();

        height = heightEditText.getText().toString();
        weight = weightEditText.getText().toString();

    }
    public boolean validate(){

        boolean valid = true;

        if (gender.equals("N/A")){
            valid = false;
        }
        if (age.equals("N/A")){
            valid = false;
        }
        if (exercise.equals("N/A")){
            valid = false;
        }
        if (bloodPressure.equals("N/A")){
            valid = false;
        }
        if (pastConditions.equals("N/A")){
            valid = false;
        }
        if (glucose.equals("N/A")){
            valid = false;
        }
        if (familyHistory.equals("N/A")){
            valid = false;
        }
        if (cholesterol.equals("N/A")){
            valid = false;
        }
        if (height.equals("N/A")){
            valid = false;
        }
        if (weight.equals("N/A")){
            valid = false;
        }
        return valid;
    }

    public void getSpinnerObjects(){

        genderSpinner = findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                this, R.array.genderArray, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ageSpinner = findViewById(R.id.ageSpinner);
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(
                this, R.array.ageArray, android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        exerciseSpinner = findViewById(R.id.exerciseSpinner);
        ArrayAdapter<CharSequence> exerciseAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        exerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(exerciseAdapter);

        bloodPressureSpinner = findViewById(R.id.bloodPressureSpinner);
        ArrayAdapter<CharSequence> bloodPressureAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        bloodPressureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodPressureSpinner.setAdapter(bloodPressureAdapter);

        pastConditionsSpinner = findViewById(R.id.pastConditionsSpinner);
        ArrayAdapter<CharSequence> conditionsAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        conditionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pastConditionsSpinner.setAdapter(conditionsAdapter);

        glucoseSpinner = findViewById(R.id.glucoseSpinner);
        ArrayAdapter<CharSequence> glucoseAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        glucoseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        glucoseSpinner.setAdapter(glucoseAdapter);

        familyHistorySpinner = findViewById(R.id.familyHistorySpinner);
        ArrayAdapter<CharSequence> familyHistoreAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        familyHistoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyHistorySpinner.setAdapter(familyHistoreAdapter);

        cholesterolSpinner = findViewById(R.id.cholesterolSpinner);
        ArrayAdapter<CharSequence> cholesterolAdapter = ArrayAdapter.createFromResource(
                this, R.array.yesNoArray, android.R.layout.simple_spinner_item);
        cholesterolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cholesterolSpinner.setAdapter(cholesterolAdapter);
    }
}

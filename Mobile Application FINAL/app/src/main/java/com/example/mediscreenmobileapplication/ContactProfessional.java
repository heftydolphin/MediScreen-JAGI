package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

public class ContactProfessional extends AppCompatActivity {

    private String gpNumber;
    private String insurerNumber;

    EditText gpNumberEditText;
    EditText insurerNumberEditText;

    Button callGPButton;
    Button callInsurerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_professional);

        gpNumberEditText = findViewById(R.id.gpNumberEditText);
        insurerNumberEditText = findViewById(R.id.insurerNumberEditText);

        callGPButton = findViewById(R.id.callGPButton);
        callInsurerButton = findViewById(R.id.callInsurerButton);

        getUserInput();

        callGPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){

                    callGP();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No GP's with that email are\n" +
                            "registered with JAGI", Toast.LENGTH_LONG).show();
                }
            }
        });

        callInsurerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){

                callInsurer();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Insurers's with that email are\n" +
                            "registered with JAGI", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void callGP(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + gpNumber));

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(callIntent);
    }

    private void callInsurer(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + insurerNumber));

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(callIntent);
    }

    private String getPhoneNumbers(){

        String phoneNumber = "";
        return phoneNumber;
    }

    public void getUserInput(){

        // Get UI components
        gpNumberEditText = findViewById(R.id.gpNumberEditText);
        insurerNumberEditText = findViewById(R.id.insurerNumberEditText);

        // Get the values of the user input
        gpNumber = gpNumberEditText.getText().toString();
        insurerNumber = insurerNumberEditText.getText().toString();
    }

    public boolean validate(){

        boolean valid = true;

        if (gpNumber.isEmpty() || gpNumber.matches("[0-9]{10}")){
            gpNumberEditText.setError("Please link with your GP first via the Add GP page");
            valid = false;
        }

        else if (insurerNumber.isEmpty() || insurerNumber.matches("[0-9]{10}")){
            gpNumberEditText.setError("Please link with your Insurer first via the Add Insurer page");
            valid = false;
        }
        return valid;
    }
}

package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserHome extends AppCompatActivity {

    User user;

    TextView userEmail;

    Button accountDetailsButton;
    Button addGPButton;
    Button addInsurerButton;
    Button medicalHistoryButton;
    Button paymentButton;
    Button contactButton;
    Button reviewButton;
    Button supportButton;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {

            user = SharedPrefManager.getInstance(this).getUser();

            userEmail = (TextView) findViewById(R.id.userEmail);

            userEmail.setTextColor(Color.parseColor("#FFFFFF"));
            userEmail.setText(user.getEmail());

            accountDetailsButton = (Button) findViewById(R.id.accountDetailsButton);
            addGPButton = (Button) findViewById(R.id.addGPButton);
            addInsurerButton = (Button) findViewById(R.id.addInsurerButton);
            medicalHistoryButton = (Button) findViewById(R.id.medicalHistoryButton);
            paymentButton = (Button) findViewById(R.id.paymentButton);
            contactButton = (Button) findViewById(R.id.contactButton);
            reviewButton = (Button) findViewById(R.id.reviewButton);
            supportButton = (Button) findViewById(R.id.supportButton);

            logoutButton = (Button) findViewById(R.id.logOutButton);

            accountDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent userDetailsActivity = new Intent(getApplicationContext(), UserDetails.class);
                    startActivity(userDetailsActivity);
                }
            });
            addGPButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            addInsurerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            medicalHistoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent medicalHistoryActivity = new Intent(getApplicationContext(), MedicalHistory.class);
                    startActivity(medicalHistoryActivity);
                }
            });
            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            contactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(getApplicationContext())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Contact Professional")
                            .setMessage("Would you like to contact your GP or Insurer")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }

                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }
            });
            reviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            supportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                    Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(MainActivity);
                }
            });

        }
    }
}

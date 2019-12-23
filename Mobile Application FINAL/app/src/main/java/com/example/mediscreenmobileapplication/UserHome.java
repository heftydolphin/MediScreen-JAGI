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

    TextView userName;

    String name;

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

            name = user.getfName() + " " + user.getlName();
            userName = findViewById(R.id.userName);

            userName.setTextColor(Color.parseColor("#FFFFFF"));

            userName.setText(name);

            accountDetailsButton = findViewById(R.id.accountDetailsButton);
            addGPButton = findViewById(R.id.addGPButton);
            addInsurerButton = findViewById(R.id.addInsurerButton);
            medicalHistoryButton = findViewById(R.id.medicalHistoryButton);
            paymentButton = findViewById(R.id.paymentButton);
            contactButton = findViewById(R.id.contactButton);
            reviewButton = findViewById(R.id.reviewButton);
            supportButton = findViewById(R.id.supportButton);

            logoutButton = findViewById(R.id.logOutButton);

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
                    Intent addGPActivity = new Intent(getApplicationContext(), AddGP.class);
                    startActivity(addGPActivity);
                }
            });
            addInsurerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addInsurerActivity = new Intent(getApplicationContext(), AddInsurer.class);
                    startActivity(addInsurerActivity);
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

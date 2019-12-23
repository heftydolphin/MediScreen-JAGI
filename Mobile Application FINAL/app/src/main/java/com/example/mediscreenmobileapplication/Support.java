package com.example.mediscreenmobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Support extends AppCompatActivity {

    EditText subjectEditText;
    EditText contentEditText;



    Spinner professionSpinner;

    Button sendFormButton;

    String subject;
    String content;
    String profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        subjectEditText = findViewById(R.id.subjectEditText);
        contentEditText = findViewById(R.id.contentEditText);

        professionSpinner = findViewById(R.id.professionSpinner);
        ArrayAdapter<CharSequence> professionAdapter = ArrayAdapter.createFromResource(
                this, R.array.professionArray, android.R.layout.simple_spinner_item);
        professionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        professionSpinner.setAdapter(professionAdapter);

        sendFormButton = findViewById(R.id.sendFormButton);

        sendFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subject = subjectEditText.getText().toString();
                content = contentEditText.getText().toString();
                profession = professionSpinner.getSelectedItem().toString();

                System.out.println("\nSubject: " + subject +
                        "\nContent: " + content +
                        "\nProfession: " + profession);
            }
        });
    }
}

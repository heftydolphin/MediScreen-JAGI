package com.example.mediscreenmobileapplication;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediscreenmobileapplication.R;

public class Rating extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView mediScreenCount,mediScreenMessage;
    private float ratedValue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        mediScreenCount = (TextView) findViewById(R.id.mediScreenCount);
        mediScreenMessage = (TextView) findViewById(R.id.mediScreenMessage);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratedValue = ratingBar.getRating();
                mediScreenCount.setText("Your Rating : "
                        + ratedValue + "/5.");

                if(ratedValue<1){
                    mediScreenMessage.setText(" No...No...!");
                }else if(ratedValue<2){
                    mediScreenMessage.setText("Ok.");
                }else if(ratedValue<3){
                    mediScreenMessage.setText("GG.");
                }else if(ratedValue<4){
                    mediScreenMessage.setText("Nice");
                }else if(ratedValue<5){
                    mediScreenMessage.setText("Very Nice");
                }else if(ratedValue==5){
                    mediScreenMessage.setText("Excellent..!!!");
                }
            }
        });
    }
}

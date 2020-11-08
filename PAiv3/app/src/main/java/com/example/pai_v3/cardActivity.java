package com.example.pai_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import okhttp3.Response;

public class cardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        TextView cardActivity = findViewById(R.id.textViewCardDetail);


        //recieve ticker value from searchView on the main activity
        Intent intent = getIntent();
        String ticker = intent.getStringExtra("ticker");// "" is for initialization



        //get detail from RapidAPI, returns response. give ticker or company name (partially or fullY)
        Response response = RapidAPI.rapidApiAutofill(ticker);

        //convert response from rapid api to string. provide mapKey from api JSON
        ArrayList<String> data = Auxiliary.responseToJSON(response, "news");


        String dataString = "";
        for(String d : data){
            dataString += d;
        }
        cardActivity.setText(dataString);






        //go back to main activity when swiped right
        cardActivity.setOnTouchListener(new Auxiliary.OnSwipeTouchListener(cardActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent(cardActivity.this, MainActivity.class); //second param is the class of where we want to go
                startActivity(intent); //this will move us to the next ativity
            }
        });
    }
}
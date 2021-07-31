package com.example.pai_v3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.Response;

import static java.util.Objects.isNull;


public class CardActivity extends AppCompatActivity {
    String ticker, currentPrice, companyName, currency, region, timezone, maxAndMinPriceRanges;
    ArrayList<Double> histPriceList = new ArrayList<Double>();
    TextView cardActivity_TickerInfo;
    LineChart cardActivity_chart;
    Button cardActivity_button_watchlist;
    WatchList wl = new WatchList();
    SharedPreferences sharedPreferences = null;

    public void updateWatchlist(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //second param is the class of where we want to go
        if(cardActivity_button_watchlist.getText() == "ADD TO WATCHLIST") {
            intent.putExtra("AddTicker", (ticker));
        }
        else{ //if(cardActivity_watchlist.getText() == "REMOVE FROM LIST")
            intent.putExtra("RemoveTicker", (ticker));
        }
        startActivity(intent); //this will move us to the next activity
    }

    public ArrayList<Double> getPaiApiResponse(String ticker) throws JSONException {
        PaiAPI api = new PaiAPI();
        JSONObject jsonResponse = null;
        try{
            jsonResponse = api.readTickerHist(ticker);
            //Log.i("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^", jsonResponse.toString());
            this.currentPrice = jsonResponse.get("currentPrice").toString();
            this.companyName = jsonResponse.get("companyName").toString();
            this.currency = jsonResponse.get("currency").toString();
            this.region = jsonResponse.get("region").toString();
            this.timezone = jsonResponse.get("timezone").toString();
            this.maxAndMinPriceRanges = jsonResponse.get("maxAndMinPriceRanges").toString();

            String s = jsonResponse.get("histPriceList").toString();
            String[] splits =  s.replace("[","").replace("]","").split(",");
            ArrayList<String> arrayList  = new ArrayList<>(Arrays.asList(splits));
            for (int i=0; i<arrayList.size(); i++)
                this.histPriceList.add(Double.parseDouble(arrayList.get(i)));
            //Log.i("************************************","HERE..");

        }catch(Exception e){
            Log.i("---CardActivity.java getPaiApiResponse Failed", ".................................");
        }
        //JSONArray jArr = jsonResponse.getJSONArray("tickerData");
        //this.ticker = jArr.getJSONObject(0).getJSONObject("ticker").toString();
        return this.histPriceList;
    }
    public void swipLeft(){
        //go back to main activity when swiped right
        cardActivity_TickerInfo.setOnTouchListener(new Auxiliary.OnSwipeTouchListener(CardActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent(CardActivity.this, MainActivity.class); //second param is the class of where we want to go
                startActivity(intent); //this will move us to the next ativity
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Double> timestampPriceList = new ArrayList<Double>();
        ArrayList<String> timestamp = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Auxiliary.onViewConnectionPolicy();//allow connections
        sharedPreferences = getSharedPreferences("WATCHLIST",MODE_PRIVATE);

        cardActivity_TickerInfo = findViewById(R.id.textViewCardTickerInfo);
        cardActivity_chart = findViewById(R.id.lineChart);
        cardActivity_button_watchlist = findViewById(R.id.buttonAddToWatchList);

        //get ticker value from searchView on the main activity
        Intent intent = getIntent();
        this.ticker = intent.getStringExtra("ticker").toUpperCase();// "" is for initialization


        if (wl.getWatchList(sharedPreferences).contains(this.ticker)){
            cardActivity_button_watchlist.setText("REMOVE FROM LIST");
        }
        else{
            Log.i("Contains@@@@@@@@@@@@@@@@@@@@@@@@@@@@", Boolean.toString(wl.getWatchList(sharedPreferences).contains(this.ticker)));
            cardActivity_button_watchlist.setText("ADD TO WATCHLIST");
        }



        try {
            timestampPriceList = getPaiApiResponse(this.ticker);

            if (timestampPriceList != null) {
                for (int i = 0; i < timestampPriceList.size(); i++) {
                    timestamp.add("");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //go back to main activity when swiped right
        swipLeft();


        new LineChartPhilJay(cardActivity_chart).build(timestamp, timestampPriceList);

        this.cardActivity_TickerInfo.setText(this.ticker + " " + this.currentPrice + " " + this.currency);


    }
}

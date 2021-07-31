/*
pending items to build:

1- if ticker doesn't exisit

 */

package com.example.pai_v3;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;
    SearchView searchView = null;
    WatchList watchlist = new WatchList();
    SharedPreferences sharedPreferences = null;

    //------------------it takes the ticker and provides it to card activity. it will move to card activity on the UI too
    public void goToCardActivity(String tickerString, Activity currentActivity) {
        Intent intent = new Intent(currentActivity, CardActivity.class); //second param is the class of where we want to go
        intent.putExtra("ticker", tickerString);
        currentActivity.startActivity(intent); //this will move us to the next activity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("WATCHLIST",MainActivity.this.MODE_PRIVATE);

        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listViewWatchlist);


        //search bar action listener
        SearchBar sb = new SearchBar();
        sb.setOnQueryTextListener(this.findViewById(R.id.searchView), searchView, MainActivity.this);


        //watchlist actions, based on cardActivity to add or remove from watchlist
        Intent intent = getIntent();
        if(intent.getStringExtra("AddTicker") != null){
            watchlist.addToWatchlist(listView, MainActivity.this, intent.getStringExtra("AddTicker"), this.sharedPreferences);
        }
        if(intent.getStringExtra("RemoveTicker") != null){
            watchlist.removeFromWatchlist(listView, MainActivity.this, intent.getStringExtra("RemoveTicker"), this.sharedPreferences);
        }

        //add the items from sharedpreferences to a list and show on screen using adaptors
        ArrayList<String> wl = watchlist.getWatchList(sharedPreferences);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, wl);//simple list adapter for strings (simple list item1)
        listView.setAdapter(arrayAdapter);//add watch list to the view
        //make each item on the list clickable, and go to cardActivity after the click
        listView.setOnItemClickListener((adapterView, view, i, l) -> MainActivity.this.goToCardActivity(wl.get(i), MainActivity.this));

    }
}

package com.example.pai_v3;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends AppCompatActivity {

    //action listener for searchView. if text inputed and submited take the ticker to the cardActivity and show sth



    public void goToCardActivity(String tickerString) {
        Intent intent = new Intent(getApplicationContext(), cardActivity.class); //second param is the class of where we want to go
        intent.putExtra("ticker", tickerString);
        startActivity(intent); //this will move us to the next ativity
        Auxiliary.onViewConnectionPolicy();//allow connections
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listViewWatchlist);

        ArrayList<String> watchlist = new ArrayList<String>();
        watchlist.add("MSFT");
        watchlist.add("FB");
        watchlist.add("GOOG");
        watchlist.add("AMZN");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, watchlist);//simple list adapter for strings (simple list item1)
        listView.setAdapter(arrayAdapter);//add family list to the view

        //make each item on the list clickable, and go to cardActivity after the click
        listView.setOnItemClickListener((adapterView, view, i, l) -> goToCardActivity(watchlist.get(i)));


        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(final String query) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(final String tickerString) {
                //pass searched item to card activity
                if (!tickerString.equals(null)) {
                    goToCardActivity(tickerString);
                }
                return false;
            }
        });
    }
}

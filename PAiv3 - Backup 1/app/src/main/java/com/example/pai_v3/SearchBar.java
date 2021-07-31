package com.example.pai_v3;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

public class SearchBar {

    //--------------------------------------------------set listener on ticker search box
    public void setOnQueryTextListener(View view, SearchView searchView, Activity currentActivity){

        /*By default the SearchView is 'iconified', which is displayed as a magnifying glass icon and only if the user clicks on the icon, then the edit field expands.
        To enable the user to click anywhere on the SearchView and expand the input field. We just need to add a click listener and call setIconified(false) when the user clicks.*/
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        //action listener : waiting for text to be inputed
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(final String query) {
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(final String tickerString) {
                //pass searched item to card activity
                MainActivity ma = new MainActivity();
                ma.goToCardActivity(tickerString, currentActivity);
                return false;
            }
        });
    }



}



package com.example.pai_v3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class WatchList {

    private void updateWatchList(ArrayList<String> watchlist, SharedPreferences sharedPreferences){
        //add ticker to sharedPreferences as a json
        Gson gson = new Gson();
        String json = gson.toJson(watchlist);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("WATCHLIST",json );
        editor.commit();
    }

    public void addToWatchlist(ListView listView, Activity currentActivity, String cardTicker, SharedPreferences sharedPreferences){

        ArrayList<String> watchlist = getWatchList(sharedPreferences);
        watchlist.add(cardTicker);
        //update json in shared preferences
        updateWatchList(watchlist, sharedPreferences);

    }

    public ArrayList<String> getWatchList(SharedPreferences sharedPreferences) {
        ArrayList<String> watchlist = new ArrayList<String>();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("WATCHLIST", "");
        Log.i("getlist...............", json.toString());
        if (json.isEmpty()) {
            Log.i("Error - WatchList Class - getWatchList Method - json is empty", "................................");
        } else {
            Type type = new TypeToken<List<String>>() {
            }.getType();
            watchlist = gson.fromJson(json, type);
        }
        return watchlist;
    }

    public void removeFromWatchlist(ListView listView, Activity currentActivity, String cardTicker, SharedPreferences sharedPreferences){
        ArrayList<String> watchlist = getWatchList(sharedPreferences);
        if(watchlist != null & watchlist.contains(cardTicker)){
            watchlist.remove(cardTicker);
            updateWatchList(watchlist, sharedPreferences);
        }
    }


}

package com.example.pai_v3;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RapidAPI {
    public static Response rapidApiAutofill(String ticker){
        //RapidAPI
        OkHttpClient client = new OkHttpClient();

        try{
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=" + ticker)
                    .get()
                    .addHeader("x-rapidapi-key", "")
                    .addHeader("x-rapidapi-host", "")
                    .build();
            Response response = client.newCall(request).execute();
            Log.i("Success - RapidAPI", "...................................");
            return response;
        }catch(Exception e){
            Log.i("Failed Start - RapidAPI", "...................................");
            e.printStackTrace();
            Log.i("Failed End - RapidAPI", "...................................");
            return null;
        }
    }

}

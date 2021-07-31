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
                    .addHeader("x-rapidapi-key", "756c72c338mshf89e9eae63dc07ep19bc08jsnd544a14df999")
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .build();
            Response response = client.newCall(request).execute();
            Log.i("Success - RapidAPI_Autofill", "...................................");
            return response;
        }catch(Exception e){
            Log.i("Failed Start - RapidAPI_Autofill", "...................................");
            e.printStackTrace();
            Log.i("Failed End - RapidAPI_Autofill", "...................................");
            return null;
        }
    }


    public static Response rapidApiGetHistData(String ticker){
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-historical-data?symbol=" + ticker)
                    .get()
                    .addHeader("x-rapidapi-key", "756c72c338mshf89e9eae63dc07ep19bc08jsnd544a14df999")
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            Log.i("Success - RapidAPI_GetHistData", "...................................");
            return response;
        }catch(Exception e){
            Log.i("Failed Start - RapidAPI_GetHistData", "...................................");
            e.printStackTrace();
            Log.i("Failed End - RapidAPI_GetHistData", "...................................");
           return null;
        }
    }

    public static Response rapidApiGetCharts(String ticker, String interval, String range){
        /*
        https://rapidapi.com/apidojo/api/yahoo-finance1?endpoint=5c1b0669e4b09c6b17cfb448
        interval allowed values are (5m | 15m | 1d | 1wk | 1mo)
        range allowed values are (1d | 5d | 3mo | 1y | 5y | max)
         */
        OkHttpClient client = new OkHttpClient();
        //+ ticker + "&interval=" + interval + "&range=" + range +

        try {
            Request request = new Request.Builder()
                    .url("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-charts?symbol=" + ticker + "&interval=" + interval + "&range=" + range + "&comparisons=%5EGDAXI%2C%5EFCHI")
                    .get()
                    .addHeader("x-rapidapi-key", "756c72c338mshf89e9eae63dc07ep19bc08jsnd544a14df999")
                    .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            Log.i("Success - RapidAPI_GetCharts", "...................................");
            return response;
        }catch(Exception e){
            Log.i("Failed Start - RapidAPI_GetCharts", "...................................");
            e.printStackTrace();
            Log.i("Failed End - RapidAPI_GetCharts", "...................................");
            return null;
        }
    }

}

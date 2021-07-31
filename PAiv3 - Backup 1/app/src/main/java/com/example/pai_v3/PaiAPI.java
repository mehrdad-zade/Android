package com.example.pai_v3;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class PaiAPI {

    //-----------------------------------------------------------------------get hist of one ticker
    public JSONObject readTickerHist(String ticker) throws IOException {
        String backendRout = "tickerInfo";
        JSONObject jsonResponse = null;
        try{
            Request request = requestOneTicker(ticker, backendRout);
            jsonResponse = getPaiApiRequest(request);
        }catch(Exception e){
            e.printStackTrace();
            Log.i("PaiApi.java readTickerHist Failed", ".................................");
        }
        return jsonResponse;
    }

    //-----------------------------------------------------------------------get hist of ALL tickers
    public void readAllHist(){
        String backendRout = "hist_tickers";
        try{
            Request request = requestAllTickers(backendRout);
            getPaiApiRequest(request);
            //Log.i("Result----------------------------------------", result);
        }catch(Exception e){
            e.printStackTrace();
            Log.i("PaiApi.java readAllHist Failed", ".................................");
        }
    }

    //-----------------------------------------------------------------------get current of ticker
    public void readTickerCurrent(){
        String backendRout = "ticker_current_data";
        try{
            Request request = requestAllTickers(backendRout);
            getPaiApiRequest(request);
            //Log.i("Result----------------------------------------", result);
        }catch(Exception e){
            Log.i("PaiApi.java readTickerCurrent Failed", ".................................");
            e.printStackTrace();
        }
    }

    //-----------------------------------------------------------------------prep req with ticker
    public Request requestOneTicker(String ticker, String backendRout){
        Request request = new Request.Builder()
                .url("https://petaintelligence.herokuapp.com/pai_api/" + backendRout + "/?ticker=" + ticker)//add params at the end of api url
                .addHeader("Authorization", "Token a2f8984104a20e88715722933dc5c396968234b8")
                .build();
        return request;
    }

    //-----------------------------------------------------------------------prep req withOUT ticker
    public Request requestAllTickers(String backendRout){
        Request request = new Request.Builder()
                .url("https://petaintelligence.herokuapp.com/pai_api/tickerInfo/")
                .addHeader("Authorization", "Token a2f8984104a20e88715722933dc5c396968234b8")
                .build();
        return request;
    }

    //-----------------------------------------------------------------------return string res of req
    public JSONObject getPaiApiRequest(Request request) throws IOException, JSONException, ExecutionException, InterruptedException {

        class CallbackFuture extends CompletableFuture<Response> implements Callback {
            public void onResponse(Call call, Response response) {
                super.complete(response);
            }
            public void onFailure(Call call, IOException e){
                super.completeExceptionally(e);
            }
        }


        OkHttpClient client = new OkHttpClient();

        CallbackFuture future = new CallbackFuture();
        client.newCall(request).enqueue(future);
        Response response = future.get();
        //Log.i("PAI^^^^^^^^^^^^^^^^^^^^^^^^^^", response.body().string());
        return new JSONObject(response.body().string()).getJSONArray("tickerData").getJSONObject(0);

        /*
        //asynch request
        client.newCall(request)
                .enqueue(new Callback() {//enqueue will run this request in a background thread. we cannot make http requests in main thread
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error
                        Log.i("PaiApi.java Asynch Failed", ".................................");
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        // Do something with the response
                        if(response.isSuccessful()) {
                            try {
                                Jobj = new JSONObject(response.body().string()).getJSONArray("tickerData").getJSONObject(0);

                                //Log.i("PAI^^^^^^^^^^^^^^^^^^^^^^^^^^", Jobj[0].get("ticker").toString());
                                //Log.i("PAI^^^^^^^^^^^^^^^^^^^^^^^^^^", Jobj[0].toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //Log.i("Success ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^", response.body().string());//cannot make "response.body().string()" twice
                        }
                    }

                });
        Log.i("PAI^^^^^^^^^^^^^^^^^^^^^^^^^^", Jobj.toString());

         */

    }
}

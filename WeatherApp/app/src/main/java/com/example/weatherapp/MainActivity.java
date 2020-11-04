package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



//sample: https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02

public class MainActivity extends AppCompatActivity {

    JSONArray weatherArr = null;

    public void onClickSearch(View view){
        EditText cityName =  (EditText) findViewById(R.id.editTextCityName);
        Button search = (Button) findViewById(R.id.buttonSearch);
        TextView apiResult =  (TextView) findViewById(R.id.textViewApiResult);
        String city = cityName.getText().toString();
        String txt = "";

        hideSoftKeyboard(this);

        DownloadTask downloadTask = new DownloadTask();
        String result = "";
        try {
            result = downloadTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=" + city + "&appid=439d4b804bc8187953eb36d2a8c26a02").get();
            //Log.i("Result", result);
            for (int i=0; i<weatherArr.length(); i++) {
                JSONObject jsonpart = weatherArr.getJSONObject(i);
                txt += jsonpart.getString("main");//main is from the api
                txt += " : ";
                txt += jsonpart.getString("description");//description is from the api
                txt += "\n";
            }
            apiResult.setText(txt);
        }
        catch(Exception e){
            e.printStackTrace();
            apiResult.setText("Please try again later..");
        }


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while(data != -1){
                    char ch = (char) data;
                    result += ch;
                    data = inputStreamReader.read();
                }
                Log.i("info", "Reading..................");
                return result;
            }catch(Exception e){
                e.printStackTrace();
                return "Failed..............................";
            }
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String weather = jsonObject.getString("weather");//"weather" is a key in the api
                Log.i("Weather info", weather);
                weatherArr = new JSONArray(weather);

                /*
                for (int i=0; i<weather.length(); i++) {
                    JSONObject jsonpart = weatherArr.getJSONObject(i);
                    Log.i("Main", jsonpart.getString("main"));//main is from the api
                    Log.i("Description", jsonpart.getString("description"));//description is from the api
                }
                Log.i("...", "_____________________________________________");
                 */
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void onClickConvert(View view) {

        EditText usd = (EditText) findViewById(R.id.editTextNumber4);
        EditText rate = (EditText) findViewById(R.id.editTextNumber5);

        hideSoftKeyboard(this);

        Toast.makeText(this, Double.toString(Double.parseDouble(usd.getText().toString()) * Double.parseDouble(rate.getText().toString())) + " EUR", Toast.LENGTH_LONG).show();

        usd.setText("");
        rate.setText("");
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
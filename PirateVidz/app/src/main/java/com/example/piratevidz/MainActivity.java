package com.example.piratevidz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View view = null;

    SearchView searchView = null;

    TextView textViewSource1 = null;
    TextView textViewSource2 = null;
    TextView textViewSource3 = null;
    TextView textViewSource4 = null;
    TextView textViewSource5 = null;
    TextView textViewSource6 = null;

    RadioButton radioButton1 = null;
    RadioButton radioButton2 = null;
    RadioButton radioButton3 = null;
    RadioButton radioButton4 = null;
    RadioButton radioButton5 = null;
    RadioButton radioButton6 = null;


    public void setDefaultSources(){
        textViewSource1 = findViewById(R.id.textViewSite1);
        textViewSource2 = findViewById(R.id.textViewSite2);
        textViewSource3 = findViewById(R.id.textViewSite3);
        textViewSource4 = findViewById(R.id.textViewSite4);
        textViewSource5 = findViewById(R.id.textViewSite5);
        textViewSource6 = findViewById(R.id.textViewSite6);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton6 = findViewById(R.id.radioButton6);

        textViewSource1.setText("https://thepiratebay.org/index.html");
        textViewSource2.setText("https://tpbpirateproxy.org/en");
        textViewSource3.setText("https://thepiratebay.us.org/en/");
        textViewSource4.setText("https://thepiratebays3.com/english/");

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.view;



        setDefaultSources();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                //RadioButton rb = (RadioButton) findViewById(checkedId);

                String url = null;
                switch (checkedId) {
                    case R.id.radioButton1:
                        url = textViewSource1.getText().toString();
                    case R.id.radioButton2:
                        url = textViewSource2.getText().toString();
                    case R.id.radioButton3:
                        url = textViewSource3.getText().toString();
                    case R.id.radioButton4:
                        url = textViewSource4.getText().toString();
                    case R.id.radioButton5:
                        url = textViewSource5.getText().toString();
                    case R.id.radioButton6:
                        url = textViewSource6.getText().toString();
                }


                try{
                    WebView webView = findViewById(R.id.webView);
                    webView.setVisibility(View.VISIBLE);
                    webView.getSettings().setJavaScriptEnabled(true);
                    //webView.setWebViewClient(new WebViewClient());//this argument makes sure the web content is opened within the app. if not provided default browser will kick in
                    webView.loadUrl("https://thepiratebay.org/index.html");
                    Log.i("info................", "was here");
                    //setContentView(webView);
                }catch (Exception e){
                    Log.i("Error......................" , e.toString());
                }



            }
        });

    }
}
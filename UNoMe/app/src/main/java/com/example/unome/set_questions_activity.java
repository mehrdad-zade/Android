package com.example.unome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class set_questions_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_questions_activity);
    }

    public void getDefaultQuestions(){
        DefaultQuestions dq = new DefaultQuestions();
        HashMap<String, ArrayList<String>>  defaultQsAs = dq.getDefaultQsAs();
        
    }
}
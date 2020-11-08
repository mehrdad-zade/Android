package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //values saved in sharePreferences can be shared between apps or later use by our own app

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);//1st argument is the app name and 2nd is the way you want to save your share pref

        sharedPreferences.edit().putString("Name", "Mehrdad").apply();//"Name" is just the key, value saved is "Mehrdad"

        sharedPreferences.getString("Name", ""); //what you want to get is the value for key "Name", if it couldn't find it it will assign ""

        //save array of strings to sharedPreferences
        ArrayList<String> friends = new ArrayList<>();

        friends.add("Fido");
        friends.add("Sarah");
        friends.add("Jones");

        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();

            Log.i("friends",ObjectSerializer.serialize(friends));

        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("new Friends",newFriends.toString());

        //sharedPreferences.edit().putString("username","nick").apply();

        //String username = sharedPreferences.getString("username","");

        //Log.i("This is the username",username);

    }
}
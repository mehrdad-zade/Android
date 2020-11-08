package com.example.sqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //create db and add data to it
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS eventsTable (name VARCHAR, year INT(4))");
            sqLiteDatabase.execSQL("INSERT INTO eventsTable (name, year) VALUES ('Milenieum',2000)");
            sqLiteDatabase.execSQL("INSERT INTO eventsTable (name, year) VALUES ('Mehrdad',2014)");
            //read data from db
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM eventsTable", null);
            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();
            //loop through the data
            while (c != null) {
                Log.i("Results - name", c.getString(nameIndex));
                Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));

                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
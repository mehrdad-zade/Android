package com.example.pai_v3;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Objects;
public class LineChartPhilJay {

    //Chart params
    com.github.mikephil.charting.charts.LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;

    LineChartPhilJay(com.github.mikephil.charting.charts.LineChart lc){
        lineChart = lc;
        lineData = null;
        lineDataSet = null;
        lineEntries = null;
    }

    public void getChartData(ArrayList<String> timestamp, ArrayList<Double> timestampPriceList){
        lineEntries = new ArrayList<>();

        for(int i=0; i<timestamp.size(); i++){
            lineEntries.add(new Entry(Float.parseFloat(Integer.toString(i)+"f"), Float.parseFloat((Math.round(timestampPriceList.get(i) * 100.0) / 100.0)+"")));

        }

        /*sample
        lineEntries.add(new Entry(2f, 0));
        lineEntries.add(new Entry(4f, 1));
        lineEntries.add(new Entry(6f, 1));
        lineEntries.add(new Entry(8f, 3));
        lineEntries.add(new Entry(10f, 4));
        lineEntries.add(new Entry(12f, 3));
         */

    }

    public void build(ArrayList<String> timestamp, ArrayList<Double> timestampPriceList){
        getChartData(timestamp, timestampPriceList);
        lineDataSet = new LineDataSet(lineEntries, "");
        lineDataSet.setDrawValues(false);//remove value lables from the chart line


        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        //lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        //lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(18f);

        //removes vertical and horizantal lines of the background grid/mesh
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);

        //To disable the right yAxis
        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false);

        //show the x axis at the bottom rather than the default top
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        //change x axix color
        lineChart.getXAxis().setTextColor(Color.WHITE);
        //change Y axix color
        lineChart.getAxisLeft().setTextColor(Color.WHITE);

        //remove "description label" from buttom right corner
        lineChart.getDescription().setEnabled(false);

        //remove the circles from the line
        lineDataSet.setDrawCircles(false);
    }
}
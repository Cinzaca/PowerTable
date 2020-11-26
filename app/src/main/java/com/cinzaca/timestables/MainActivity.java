package com.cinzaca.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        ListView myListView;

        public void generatePowerTable(int powerTableNumber, ArrayList<ArrayList<Integer>> myList) {

            ArrayAdapter<Integer> myArrayAdapter = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1, myList.get(powerTableNumber));

            myListView.setAdapter(myArrayAdapter);
        }

        public ArrayList<ArrayList<Integer>> fillList(int maxNumber, int maxPower) {

            ArrayList<ArrayList<Integer>> myList = new ArrayList<ArrayList<Integer>>();

            for (int i = 1; i <= maxNumber; i++) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int j = 0; j <= maxPower; j++) {
                    temp.add((int) Math.pow(i, j));
                }
                myList.add(temp);
            }
            return myList;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListView = findViewById(R.id.myListView);
        final SeekBar mySeekBar = findViewById(R.id.mySeekBar);
        mySeekBar.setMax(fillList(10, 10).size() - 1);

        int startingPosition = 3;
        generatePowerTable(startingPosition, fillList(10, 10));
        mySeekBar.setProgress(startingPosition);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int powerTableNumber;

                if(progress < min) {
                    powerTableNumber = min;
                    mySeekBar.setProgress(min);
                } else {
                    powerTableNumber = progress;
                }
                generatePowerTable(powerTableNumber, fillList(10, 10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
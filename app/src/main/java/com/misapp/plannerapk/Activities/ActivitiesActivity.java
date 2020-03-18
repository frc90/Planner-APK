package com.misapp.plannerapk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.misapp.plannerapk.R;

import java.util.ArrayList;

public class ActivitiesActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_planner_logo_foreground);

        listView = findViewById(R.id.listView);

        ArrayList<String> people = new ArrayList<String>();
        people.add("persona1");
        people.add("persona2");
        people.add("persona3");
        people.add("persona4");
        people.add("persona5");
        people.add("persona6");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, people);

        listView.setAdapter(adapter);
    }
}

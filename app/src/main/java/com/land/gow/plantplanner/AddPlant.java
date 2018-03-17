package com.land.gow.plantplanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class AddPlant extends AppCompatActivity {
    private static final String LOG_TAG = AddPlant.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showNutriantDatePickerDialog(View view) {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "DatePickerFragment");

    }

}

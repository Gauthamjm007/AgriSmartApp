package com.example.arunn.silfraagri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class weathercondition_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathercondition_activity);
        Intent launchintent = getPackageManager().getLaunchIntentForPackage("com.example.lenovo.weatherapp");
        startActivity(launchintent);
    }
}

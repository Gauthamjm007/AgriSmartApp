package com.example.arunn.silfraagri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class pesticidesnew_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesticidesnew_activity);

        Intent launchintent = getPackageManager().getLaunchIntentForPackage("sup.savemeaspot");
        startActivity(launchintent);
    }
}

package com.example.arunn.silfraagri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NpkRatio extends AppCompatActivity {

    public Button buttonclicked1;
    public Button buttonclicked2;
    public Button buttonclicked3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npk_ratio);
    }


    public void buttonclicked1(View view) {
        Intent intent = new Intent(NpkRatio.this, N_page.class);
        startActivity(intent);
    }

    public void buttonclicked2(View view) {
        Intent intent = new Intent(NpkRatio.this, P_Page.class);
        startActivity(intent);
    }

    public void buttonclicked3(View view) {
        Intent intent = new Intent(NpkRatio.this, K_Page.class);
        startActivity(intent);
    }
}
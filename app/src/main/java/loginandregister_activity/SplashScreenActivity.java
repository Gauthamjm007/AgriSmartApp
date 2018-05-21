package loginandregister_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.arunn.silfraagri.R;

public class SplashScreenActivity extends AppCompatActivity {




    private static int SPLASH_TIME_OUT = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent i = new Intent(SplashScreenActivity.this, loginactivity.class);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

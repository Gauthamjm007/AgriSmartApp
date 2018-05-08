package soil_package;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.arunn.silfraagri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.itangqi.waveloadingview.WaveLoadingView;

public class soil_water_level extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    String q1;
    int y1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_water_level);


        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        final WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q1 = dataSnapshot.child("sensors").child("soil").child("humidity").getValue().toString();

                y1=Integer.parseInt(q1);

                Toast.makeText(soil_water_level.this, q1, Toast.LENGTH_SHORT).show();

                mWaveLoadingView.setTopTitle("Soil Moisture Level");
                mWaveLoadingView.setTopTitleColor(Color.BLACK);
                mWaveLoadingView.setCenterTitle("50%");

                if(y1<30){
                    mWaveLoadingView.setProgressValue(y1);
                    mWaveLoadingView.setCenterTitle(q1+"%");
                    mWaveLoadingView.setWaveColor(Color.RED);
                }
                else if(y1>=30 && y1<90){
                    mWaveLoadingView.setProgressValue(y1);
                    mWaveLoadingView.setCenterTitle(q1+"%");
                    mWaveLoadingView.setWaveColor(Color.rgb(52, 152, 219));

                }

                else if (y1 >=90){

                    mWaveLoadingView.setProgressValue(y1);
                    mWaveLoadingView.setCenterTitle(q1+"%");
                    mWaveLoadingView.setTopTitleColor(Color.WHITE);
                    mWaveLoadingView.setWaveColor(Color.GREEN);
                    mWaveLoadingView.setCenterTitleColor(Color.WHITE);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

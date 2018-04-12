package weather_package;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arunn.silfraagri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class RainProbabilityFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    ImageView rainprobability_image;
    TextView rainprobability_view;
    String q;
    int y;

    public RainProbabilityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rain_probability, container, false);
        rainprobability_image = (ImageView) view.findViewById(R.id.rainprobability_image);
        rainprobability_view=(TextView) view.findViewById(R.id.rainprobability_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q = dataSnapshot.child("sensors").child("rainprob").getValue().toString();
                Toast.makeText(getContext(),q, Toast.LENGTH_SHORT).show();
                y=Integer.parseInt(q);


                if (y==1){
                    rainprobability_image.setImageResource(R.drawable.rainverylow);
                    rainprobability_view.setText("The Probability of Rain is Very Low" );
                }
                else if (y==2){
                    rainprobability_image.setImageResource(R.drawable.rainlow);
                    rainprobability_view.setText("The Probability of Rain is Low");
                }
                else if (y==3){
                    rainprobability_image.setImageResource(R.drawable.rainmedium);
                    rainprobability_view.setText("The Probability of Rain is Medium");
                }
                else if (y==4){
                    rainprobability_image.setImageResource(R.drawable.rainhigh);
                    rainprobability_view.setText("The Probability of Rain is High");
                }
                else{
                    rainprobability_image.setImageResource(R.drawable.rainveryhigh);
                    rainprobability_view.setText("The Probability of Rain is Very High");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}

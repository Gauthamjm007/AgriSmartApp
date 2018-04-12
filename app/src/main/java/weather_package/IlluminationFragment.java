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
public class IlluminationFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    ImageView illumination_image;
    TextView illumination_view;
    String q;
    int y;

    public IlluminationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_illumination, container, false);
        illumination_image = (ImageView) view.findViewById(R.id.illuminations_image);
        illumination_view=(TextView) view.findViewById(R.id.illminations_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q = dataSnapshot.child("sensors").child("illumination").getValue().toString();
                Toast.makeText(getContext(),q, Toast.LENGTH_SHORT).show();
                y=Integer.parseInt(q);


                if (y==1){
                    illumination_image.setImageResource(R.drawable.illuminationlow);
                   illumination_view.setText("The Illumination is Low" );
                }
                else if (y==2){
                    illumination_image.setImageResource(R.drawable.illuminationnormal);
                  illumination_view.setText("The Illumination is Moderate");
                }

                else{
                    illumination_image.setImageResource(R.drawable.illuminationhigh);
                    illumination_view.setText("The Illumination is High");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}

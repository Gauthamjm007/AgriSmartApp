package info_bar_package;


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



public class DeviceTempFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    ImageView battery_temp;
    TextView bat_temp_view;
    String q;
    int y;

    public DeviceTempFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();

        View view = inflater.inflate(R.layout.fragment_device_temp, container, false);
        battery_temp = (ImageView) view.findViewById(R.id.battery_temp);
        bat_temp_view=(TextView) view.findViewById(R.id.bat_temp_view);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q = dataSnapshot.child("sensors").child("batterytemp").getValue().toString();
                Toast.makeText(getContext(),q, Toast.LENGTH_SHORT).show();
                y=Integer.parseInt(q);


                if (y>=0 && y<=15){
                    battery_temp.setImageResource(R.drawable.devicecool);
                    bat_temp_view.setText("The Device Temprature is : " +""+q+"°C" );
                }
                else if (y>=16 && y <=30){
                    battery_temp.setImageResource(R.drawable.devicenormal);
                    bat_temp_view.setText("The Device Temprature is : " +""+q+"°C");
                }
                else{
                    battery_temp.setImageResource(R.drawable.devicehot);
                    bat_temp_view.setText("The Device Temprature is : " +""+q+"°C");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       return view;
    }

}

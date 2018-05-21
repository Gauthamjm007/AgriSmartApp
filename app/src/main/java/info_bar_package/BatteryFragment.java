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



public class BatteryFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    ImageView iv_battery;
    TextView battery_the_temp;
    String q;
    int y;


    public BatteryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();

        View view = inflater.inflate(R.layout.fragment_battery, container, false);
        iv_battery = (ImageView) view.findViewById(R.id.iv_battery);
        battery_the_temp=(TextView)view.findViewById(R.id.bat_temp_view);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q = dataSnapshot.child("sensors").child("battery").getValue().toString();
                Toast.makeText(getContext(),q +"%", Toast.LENGTH_SHORT).show();
                y=Integer.parseInt(q);



                         if(y==0){
                             iv_battery.setImageResource(R.drawable.low);
                             //battery_the_temp.setText("The Battery Level is : 0 %");
                         }

                         else if (y>0&&y<=25){
                             iv_battery.setImageResource(R.drawable.twenty);
                            // battery_the_temp.setText("The Battery Level is : 25 %");

                         }

                         else if(y>=26 && y<=50){
                             iv_battery.setImageResource(R.drawable.fifty);
                            // battery_the_temp.setText("The Battery Level is : 50 %");

                         }
                         else if(y>=51 && y<=75){
                             iv_battery.setImageResource(R.drawable.eighty);
                            // battery_the_temp.setText("The Battery Level is : 75 %");

                         }
                          else{
                             iv_battery.setImageResource(R.drawable.hundred);
                            // battery_the_temp.setText("The Battery Level is : 100 %");

                         }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

   return view;
    }

}

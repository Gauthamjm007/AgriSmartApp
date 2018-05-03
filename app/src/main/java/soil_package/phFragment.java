package soil_package;


import android.app.ProgressDialog;
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


public class phFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    ImageView soil_ph_scale;
    TextView soil_ph_value;
    TextView soil_ph_type;
    String q ;
    Float y;
    private ProgressDialog loadingBar;


    public phFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        loadingBar=new ProgressDialog(getActivity());


        View view =  inflater.inflate(R.layout.fragment_ph, container, false);
        soil_ph_scale = (ImageView) view.findViewById(R.id.soil_ph_scale);
        soil_ph_value=(TextView) view.findViewById(R.id.soil_ph_value);
        soil_ph_type=(TextView) view.findViewById(R.id.soil_ph_type);




        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q = dataSnapshot.child("sensors").child("soil").child("pH").getValue().toString();
                Toast.makeText(getContext(),q, Toast.LENGTH_SHORT).show();
                y=Float.parseFloat(q);



                if (y>=0 && y<1){
                    soil_ph_scale.setImageResource(R.drawable.ph0);
                    soil_ph_value.setText("The pH value is : " +""+q );
                    soil_ph_type.setText("The Soil is very highly Acidic");
                }
                else if (y>=1 && y <2){
                    soil_ph_scale.setImageResource(R.drawable.ph1);
                    soil_ph_value.setText("The pH value is : " +""+q );
                    soil_ph_type.setText("The Soil is highly Acidic");
                }
                else if (y>=2 && y <3){
                    soil_ph_scale.setImageResource(R.drawable.ph2);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Acidic");
                }
                else if (y>=3 && y <4){
                    soil_ph_scale.setImageResource(R.drawable.ph3);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Acidic");
                }
                else if (y>=4 && y <5){
                    soil_ph_scale.setImageResource(R.drawable.ph4);
                    soil_ph_value.setText("The pH level is : " +""+q );
                    soil_ph_type.setText("The Soil is Acidic");
                }
                else if (y>=5 && y <6){
                    soil_ph_scale.setImageResource(R.drawable.ph5);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Acidic");

                }
                else if (y>=6 && y <7){
                    soil_ph_scale.setImageResource(R.drawable.ph6);
                    soil_ph_value.setText("The pH level is : " +q );
                    soil_ph_type.setText("The Soil is slightly Acidic");
                }
                else if (y==7 ){
                    soil_ph_scale.setImageResource(R.drawable.ph7);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Neutral");
                }

                else if (y>7 && y <8){
                    soil_ph_scale.setImageResource(R.drawable.ph7);
                    soil_ph_value.setText("The pH level is : " +""+q );
                    soil_ph_type.setText("The Soil is slightly Alkaline");
                }
                else if (y>=8 && y <9){
                    soil_ph_scale.setImageResource(R.drawable.ph8);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Alkaline");
                }

                else if (y>=9 && y <10){
                    soil_ph_scale.setImageResource(R.drawable.ph9);
                    soil_ph_value.setText("The pH level is : " +""+q );
                    soil_ph_type.setText("The Soil is Alkaline");
                }
                else if (y>=10 && y <11){
                    soil_ph_scale.setImageResource(R.drawable.ph10);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is Alkaline");
                }
                else if (y>=11 && y <12){
                    soil_ph_scale.setImageResource(R.drawable.ph11);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is highly Alkaline");
                }
                else if (y>=12 && y <13){
                    soil_ph_scale.setImageResource(R.drawable.ph12);
                    soil_ph_value.setText("The pH level is : " +""+q );
                    soil_ph_type.setText("The Soil is highly Alkaline");
                }
                else if (y>=13 && y <14){
                    soil_ph_scale.setImageResource(R.drawable.ph13);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is highly Alkaline");
                }

                else{
                    soil_ph_scale.setImageResource(R.drawable.ph14);
                    soil_ph_value.setText("The pH value  is : " +""+q );
                    soil_ph_type.setText("The Soil is very highly Alkaline ");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}

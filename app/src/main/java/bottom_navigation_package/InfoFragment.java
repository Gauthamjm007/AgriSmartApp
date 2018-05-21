package bottom_navigation_package;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import info_bar_package.BatteryFragment;
import info_bar_package.DeviceTempFragment;

import info_bar_package.MapsActivity;
import com.example.arunn.silfraagri.R;



public class InfoFragment extends Fragment {

    String d,e;


    private ImageButton DeviceBatteryInfo;
    private ImageButton MapButton;
    private ImageButton DeviceTemprature;

    public InfoFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        DeviceBatteryInfo=(ImageButton)view.findViewById(R.id.batterybutton);
        MapButton=(ImageButton)view.findViewById(R.id.mapbutton);
        DeviceTemprature=(ImageButton)view.findViewById(R.id.devicetempbutton);



        DeviceBatteryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BatteryFragment batteryfragment = new BatteryFragment();
                android.support.v4.app.FragmentTransaction batteryfragmentTransaction = getFragmentManager().beginTransaction();
                batteryfragmentTransaction.replace(R.id.frag, batteryfragment, "Battery Fragment");
                batteryfragmentTransaction.commit();

            }
        });

        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* MapFragment mapfragment = new MapFragment();
               android.support.v4.app.FragmentTransaction mapfragmentTransaction = getFragmentManager().beginTransaction();
               mapfragmentTransaction.replace(R.id.frag, mapfragment, "Battery Fragment");
                  mapfragmentTransaction.commit();*/
               Intent i = new Intent(getActivity(),MapsActivity.class);
               startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);

            }
        });
        DeviceTemprature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceTempFragment devicetempfragment = new DeviceTempFragment();
                android.support.v4.app.FragmentTransaction devicetempfragmentTransaction = getFragmentManager().beginTransaction();
                devicetempfragmentTransaction.replace(R.id.frag, devicetempfragment, "Device Temprature Fragment");
                devicetempfragmentTransaction.commit();
            }
        });
        return view;
    }



}

package bottom_navigation_package;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.arunn.silfraagri.R;
import com.example.arunn.silfraagri.pesticidesnew_activity;
import com.example.arunn.silfraagri.weathercondition_activity;

import weather_package.WeatherConditionsFragment;

import weather_package.IlluminationFragment;
import weather_package.RainProbabilityFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    private ImageButton WeatherCondition;
    private ImageButton RainProbability;
    private ImageButton Illumination;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        WeatherCondition=(ImageButton)view.findViewById(R.id.weatherconditionbutton);
        RainProbability=(ImageButton)view.findViewById(R.id.rainprobabilitybutton);
        Illumination=(ImageButton)view.findViewById(R.id.illuminationbutton);

        WeatherCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), weathercondition_activity.class);
                startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);

            }
        });

        RainProbability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RainProbabilityFragment rainprobabiltiyfragment = new RainProbabilityFragment();
                android.support.v4.app.FragmentTransaction rainprobabilityfragmentTransaction = getFragmentManager().beginTransaction();
                rainprobabilityfragmentTransaction.replace(R.id.frag, rainprobabiltiyfragment, "Rain Probability Fragment");
                rainprobabilityfragmentTransaction.commit();
            }
        });
        Illumination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IlluminationFragment illuminationdevice = new IlluminationFragment();
                android.support.v4.app.FragmentTransaction illuminationfragmentTransaction = getFragmentManager().beginTransaction();
                illuminationfragmentTransaction.replace(R.id.frag,illuminationdevice , "Device Temprature Fragment");
                illuminationfragmentTransaction.commit();
            }
        });
        return view;
    }


}

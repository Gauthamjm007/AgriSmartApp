package bottom_navigation_package;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.arunn.silfraagri.NPK_PieChart;
import com.example.arunn.silfraagri.R;
import com.example.arunn.silfraagri.soil_water_level;

import SuggestionActivity.Suggestions;
import soil_package.SoilHumidityFragment;
import soil_package.npkFragment;
import soil_package.phFragment;




public class SoilFragment extends Fragment {

    private ImageButton SoilHumidityButton;
    private ImageButton phButton;
    private ImageButton npkButton;


    public SoilFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soil, container, false);
        SoilHumidityButton=(ImageButton)view.findViewById(R.id.soilhumiditybutton);
        phButton=(ImageButton)view.findViewById(R.id.phbutton);
        npkButton=(ImageButton)view.findViewById(R.id.npkbutton);

        SoilHumidityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), soil_water_level.class);
                startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);

            }
        });

        phButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phFragment phfragment = new phFragment();
                android.support.v4.app.FragmentTransaction phfragmentTransaction = getFragmentManager().beginTransaction();
                phfragmentTransaction.replace(R.id.frag, phfragment, "pH Fragment");
                phfragmentTransaction.commit();
            }
        });
        npkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NPK_PieChart.class);
                startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);
            }
        });
        return view;
    }


}
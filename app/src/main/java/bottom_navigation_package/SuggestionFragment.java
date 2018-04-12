package bottom_navigation_package;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import suggestions_package.Crops_grown_Fragment;
import suggestions_package.Fertilizers_suggestion_Fragment;
import com.example.arunn.silfraagri.R;
import suggestions_package.pesticides_Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionFragment extends Fragment {


    private ImageButton PesticidesButton;
    private ImageButton FertilizersButton;
    private ImageButton CropSuggestionButton;

    public SuggestionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);

        PesticidesButton=(ImageButton)view.findViewById(R.id.pesticidesbutton);
        FertilizersButton=(ImageButton)view.findViewById(R.id.plantfertilizerbutton);
        CropSuggestionButton=(ImageButton)view.findViewById(R.id.cropsuggestionbutton);

        PesticidesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesticides_Fragment pesticidesfragment = new  pesticides_Fragment();
                android.support.v4.app.FragmentTransaction pesticidesfragmentTransaction = getFragmentManager().beginTransaction();
                pesticidesfragmentTransaction.replace(R.id.frag, pesticidesfragment, "Pesticides Fragment");
                pesticidesfragmentTransaction.commit();

            }
        });

        FertilizersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fertilizers_suggestion_Fragment fertilizerssuggestionfragment = new Fertilizers_suggestion_Fragment();
                android.support.v4.app.FragmentTransaction fertilizerssuggestionfragmentTransaction = getFragmentManager().beginTransaction();
                fertilizerssuggestionfragmentTransaction.replace(R.id.frag, fertilizerssuggestionfragment, "Fertilizers Fragment");
                fertilizerssuggestionfragmentTransaction.commit();
            }
        });
        CropSuggestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Crops_grown_Fragment cropsgrownfragment = new Crops_grown_Fragment();
                android.support.v4.app.FragmentTransaction cropsgrownfragmentTransaction = getFragmentManager().beginTransaction();
                cropsgrownfragmentTransaction.replace(R.id.frag,cropsgrownfragment , "Device Temprature Fragment");
                cropsgrownfragmentTransaction.commit();
            }
        });

        return view;


    }

}

package suggestions_package;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.arunn.silfraagri.K_Page;
import com.example.arunn.silfraagri.N_page;
import com.example.arunn.silfraagri.P_Page;
import com.example.arunn.silfraagri.R;
import com.example.arunn.silfraagri.pesticidesnew_activity;

import SuggestionActivity.Suggestions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fertilizers_suggestion_Fragment extends Fragment {

    private ImageButton NitrogenButton;
    private ImageButton PotassiumButton;
    private ImageButton PhosphorousButton;

    public Fertilizers_suggestion_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fertilizers_suggestion_, container, false);

        NitrogenButton = (ImageButton) view.findViewById(R.id.nitrogen_button);
        PotassiumButton = (ImageButton) view.findViewById(R.id.phosphorous_button);
        PhosphorousButton = (ImageButton) view.findViewById(R.id.potassium_button);

        NitrogenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), N_page.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        PotassiumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), P_Page.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });
        PhosphorousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), K_Page.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        return view;


    }
}

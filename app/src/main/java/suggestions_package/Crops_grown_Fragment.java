package suggestions_package;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arunn.silfraagri.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Crops_grown_Fragment extends Fragment {


    public Crops_grown_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crops_grown_, container, false);
    }

}

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
import com.example.arunn.silfraagri.notification;

import info_bar_package.MapsActivity;
import suggestions_package.pesticides_Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment  {

    public ImageButton notificatonicon;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        notificatonicon=(ImageButton)view.findViewById(R.id.imagenotification);
        notificatonicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(),MapsActivity.class);
                startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);

            }
        });

        return view;
    }



}

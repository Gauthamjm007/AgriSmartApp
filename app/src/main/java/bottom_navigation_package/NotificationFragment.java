package bottom_navigation_package;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import darshanNotification.NotificationActivity;
import com.example.arunn.silfraagri.R;
import com.nex3z.notificationbadge.NotificationBadge;


public class NotificationFragment extends Fragment  {

    public ImageButton notificatonicon;
    NotificationBadge mbadge;

    public NotificationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        notificatonicon=(ImageButton)view.findViewById(R.id.imagenotification);
        mbadge=(NotificationBadge) view.findViewById(R.id.badge);
        mbadge.setNumber(5);

        notificatonicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
                ((Activity)getActivity()).overridePendingTransition(0,0);
            }
        });
        return view;
    }






}

package pesticides_map;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;



public class CustomMapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Activity context;
    private String locality;
    private String category;
    private String description;

    public CustomMapInfoWindowAdapter(Activity context, String locality, String category, String description){
        this.context = context;
        this.locality = locality;
        this.category = category;
        this.description = description;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.custom_info_window_marker, null);

        //Hämtar alla textview från layout
        TextView spotTitleTV = view.findViewById(R.id.info_window_title);
        TextView spotCategoryTV = view.findViewById(R.id.info_window_category);
        TextView spotDescriptionTV = view.findViewById(R.id.info_window_description);
        TextView localityTV = view.findViewById(R.id.info_window_real_location);
        TextView coordinatesTV = view.findViewById(R.id.info_window_coordinates);

        spotTitleTV.setText(marker.getTitle());
        spotDescriptionTV.setText(description);
        spotCategoryTV.setText(category);
        localityTV.setText(locality);
        coordinatesTV.setText(marker.getPosition().latitude + ", " + marker.getPosition().longitude);

        return view;
    }
}

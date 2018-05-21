package pesticides_map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pesticides_map.DataLayer.Spot;
import com.example.arunn.silfraagri.R;



public class SpotCollectionAdapter extends RecyclerView.Adapter<SpotCollectionAdapter.ViewHolder> {
    private List<Spot> spotDataset;
    private Context context;
    private RecyclerView dropDown;

    public SpotCollectionAdapter(Context context, List<Spot> items, RecyclerView recyclerView){
        this.context = context;
        this.spotDataset = items;
        this.dropDown = recyclerView;
    }


    @Override
    public SpotCollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout_spot_collection, parent, false);
        SpotCollectionAdapter.ViewHolder viewHolder = new SpotCollectionAdapter.ViewHolder(v, context, spotDataset);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final SpotCollectionAdapter.ViewHolder holder, final int position) {
        holder.collectionTextView.setText(spotDataset.get(position).getSpotTitle());
        holder.dropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDropdownLayoutVisibility(holder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return spotDataset.size();
    }


    public void changeDropdownLayoutVisibility(SpotCollectionAdapter.ViewHolder holder) {

        if (holder.spotDetails.getVisibility()==View.GONE) {
            holder.spotDetails.setVisibility(View.VISIBLE);
        }
        else if(holder.spotDetails.getVisibility()==View.VISIBLE){
            holder.spotDetails.setVisibility(View.GONE);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView collectionTextView;
        Button dropDownButton;
        TextView spotDescriptionTextView;
        ImageButton showMapButton;
        Context context;
        List<Spot> spots;
        RelativeLayout spotDetails;

        public ViewHolder(View v, Context context, List<Spot> spots){
            super(v);
            this.spots = spots;
            this.context = context;
            spotDetails = v.findViewById(R.id.collection_drop_down_layout);
            collectionTextView = v.findViewById(R.id.collection_text_view);
            dropDownButton = v.findViewById(R.id.collection_drop_down_button);
            spotDescriptionTextView = v.findViewById(R.id.spot_description_text_view);
            showMapButton = v.findViewById(R.id.show_map_button);
        }
    }
}

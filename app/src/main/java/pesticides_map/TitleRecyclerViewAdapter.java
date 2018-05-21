package pesticides_map;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;

import java.util.List;



public class TitleRecyclerViewAdapter extends RecyclerView.Adapter<TitleRecyclerViewAdapter.TitleViewHolder> {

    private List<String> titleDataset;

    public TitleRecyclerViewAdapter(List<String> titles){
        this.titleDataset = titles;
    }


    @Override
    public TitleRecyclerViewAdapter.TitleViewHolder onCreateViewHolder(ViewGroup parent,
    int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout_title, parent, false);
        TitleRecyclerViewAdapter.TitleViewHolder viewHolder = new TitleRecyclerViewAdapter.TitleViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(TitleRecyclerViewAdapter.TitleViewHolder holder, final int position) {
        holder.titleView.setText(titleDataset.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedTitle = titleDataset.get(position);
                View parentView = view.getRootView();
                EditText editTitle = parentView.findViewById(R.id.editTitle);
                editTitle.setText(selectedTitle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return titleDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public RelativeLayout relativeLayout;

        public TitleViewHolder(View v) {
            super(v);

            titleView = (TextView) v.findViewById(R.id.title_name_cardview);
            relativeLayout = v.findViewById(R.id.relativeLayoutTitle);
        }

        static TitleRecyclerViewAdapter.TitleViewHolder inflate(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_title, parent, false);
            return new TitleRecyclerViewAdapter.TitleViewHolder(view);
        }

    }


}



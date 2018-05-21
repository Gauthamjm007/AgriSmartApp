package fertilizer_darshan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;

import java.util.List;

public class FertilizerListAdapter extends ArrayAdapter<Fertilizer_Data> {
        List<Fertilizer_Data> FList;
        Context context;
        int resource;

        public FertilizerListAdapter(Context context, int resource, List<Fertilizer_Data> FList) {
            super(context, resource, FList);
            this.context = context;
            this.resource = resource;
            this.FList = FList;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            final View view = layoutInflater.inflate(resource, null, false);

            ImageView imageView = view.findViewById(R.id.imageView);
            final TextView textViewName = view.findViewById(R.id.textViewName);
            final TextView kg = view.findViewById(R.id.textViewType);

            final Fertilizer_Data fertilizer_data = FList.get(position);

            imageView.setImageDrawable(context.getResources().getDrawable(fertilizer_data.getImage()));
            textViewName.setText(fertilizer_data.getName(position));
            kg.setText(fertilizer_data.getkg());
            return view;
        }
    }

package SuggestionActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;

import java.util.List;

public class SuggestListAdapter extends ArrayAdapter<Suggestions_Data> {
    List<Suggestions_Data> CropList;
    Context context;
    int resource;
    String a,b,c;

    public SuggestListAdapter(Context context, int resource, List<Suggestions_Data> CropList) {
        super(context, resource, CropList);
        this.context = context;
        this.resource = resource;
        this.CropList = CropList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        final TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewType = view.findViewById(R.id.textViewType);
        final Button buttonRead = view.findViewById(R.id.buttonRead);

        final Suggestions_Data suggestions_data = CropList.get(position);

        imageView.setImageDrawable(context.getResources().getDrawable(suggestions_data.getImage()));
        textViewName.setText(suggestions_data.getName(position));
        textViewType.setText(suggestions_data.getType());



        buttonRead.setTag(position);
        buttonRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String cropname=textViewName.getText().toString().trim();
                //Toast.makeText(context,cropname, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), CropDetails.class);
                intent.putExtra("NAME", cropname);
                intent.putExtra("keyn",a);
                intent.putExtra("keyp",b);
                intent.putExtra("keyk",c);
                view.getContext().startActivity(intent);
            }


        });
        return view;
    }
}

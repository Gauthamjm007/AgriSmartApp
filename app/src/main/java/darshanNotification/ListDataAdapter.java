package darshanNotification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {
      List list = new ArrayList();
    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView  MESSAGE,DATE,TIME;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.MESSAGE = (TextView)row.findViewById(R.id.text_n_message);
            layoutHandler.DATE = (TextView)row.findViewById(R.id.text_n_date);
            layoutHandler.TIME =(TextView) row.findViewById(R.id.text_n_time);
            row.setTag(layoutHandler);
        }
        else{

            layoutHandler=(LayoutHandler) row.getTag();
        }

        Ndataprovider ndataprovider=(Ndataprovider)this.getItem(position);
        layoutHandler.MESSAGE.setText(ndataprovider.getN_message());
        layoutHandler.DATE.setText(ndataprovider.getN_date());
        layoutHandler.TIME.setText(ndataprovider.getN_time());
        return row;
    }
}

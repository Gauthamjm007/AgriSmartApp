package pesticides_map;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import pesticides_map.DataLayer.DatabaseHelper;
import pesticides_map.DataLayer.Spot;
import com.example.arunn.silfraagri.R;

public class SpotCollectionActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_collection);

        this.context = getApplicationContext();

        List<Spot> spots = DatabaseHelper.getAllSpots(context);

        RecyclerView recyclerView = findViewById(R.id.recycler_container_spot_collection);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        if(spots != null) {
            SpotCollectionAdapter adapter = new SpotCollectionAdapter(context, spots, recyclerView);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
    }
}



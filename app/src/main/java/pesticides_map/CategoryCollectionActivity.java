package pesticides_map;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import pesticides_map.DataLayer.Category;
import pesticides_map.DataLayer.SpotDatabase;
import com.example.arunn.silfraagri.R;

public class CategoryCollectionActivity extends AppCompatActivity {

    private List<Category> categories;
    private static RecyclerView recyclerView;
    private Context context;
    private static CategoryListViewAdapter adapter;
    private static RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_collection);

        recyclerView = findViewById(R.id.recycler_container_category_collection);

        SpotDatabase database = Room.inMemoryDatabaseBuilder(this.getApplicationContext(), SpotDatabase.class)
                .allowMainThreadQueries()
                .build();
        try {
            List<Category> aCat = database.getDatabase(this).categoryDao().getAllCategories();
            this.categories = aCat;
            database.close();
        } catch (Exception e) {
            e.getMessage();
        }

        adapter = new CategoryListViewAdapter(this, categories);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }
}

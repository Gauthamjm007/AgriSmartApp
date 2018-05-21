package pesticides_map;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pesticides_map.DataLayer.Category;
import pesticides_map.DataLayer.Coordinate;
import pesticides_map.DataLayer.DatabaseHelper;
import pesticides_map.DataLayer.SpotDatabase;
import com.example.arunn.silfraagri.R;

public class SaveSpotCategoryActivity extends AppCompatActivity {

    private Coordinate coordinatesToSave = new Coordinate();
    private Category categoryToSave = new Category();
    private Category newCategory = new Category();
    private List<Category> categories;
    private static RecyclerView recyclerView;
    private Context context;
    private static CategoryListViewAdapter adapter;
    private static RecyclerView.LayoutManager layoutManager;
    //Ikoner
    private int[] drawables = new int[]{R.drawable.apple,R.drawable.cherry, R.drawable.fish, R.drawable.wheat, R.drawable.water,
            R.drawable.heart, R.drawable.fire, R.drawable.building};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_spot_category);

        initBackButton();

        initAddCat();
        this.context = getApplicationContext();

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            try {

                coordinatesToSave = getIncomingCoordinateExtras(getIntent());

            } catch (NullPointerException e) {

            }
        }

        recyclerView = findViewById(R.id.recycler_container_save);

        SpotDatabase database = Room.inMemoryDatabaseBuilder(this.getApplicationContext(), SpotDatabase.class)
                .allowMainThreadQueries()
                .build();
        try {
            List<Category> aCat = database.getDatabase(this).categoryDao().getAllCategories();
            this.categories = aCat;
            database.close();
        } catch (Exception e) {

        }

        adapter = new CategoryListViewAdapter(this, categories, coordinatesToSave);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        setSpinner();

    }


    private Coordinate getIncomingCoordinateExtras(Intent intent){

        Bundle extras = intent.getExtras();
        double latitude = extras.getDouble("EXTRA_MESSAGE_COORDINATES_LAT");
        double longitude = extras.getDouble("EXTRA_MESSAGE_COORDINATES_LONG");
        String locale = extras.getString("EXTRA_MESSAGE_COORDINATES_LOCAL");
        String country = extras.getString("EXTRA_MESSAGE_COORDINATES_COUNTRY");
        return new Coordinate(latitude,longitude,locale,country);
    }



    private void initBackButton() {
        Button back = this.findViewById(R.id.category_cancel);
        back.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    finish();
                }
            });
    }

    private void setSpinner(){

            CustomSpinnerAdapter customAdapter = new CustomSpinnerAdapter(SaveSpotCategoryActivity.this, drawables);
            customAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
            spinner.setAdapter(customAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int resourceId = drawables[i];
                    newCategory.setCategoryImg(resourceId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    private void initAddCat() {
            Button addCatBtn = this.findViewById(R.id.buttonAddCategory);

            addCatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView categoryField = findViewById(R.id.addNewCategory);
                    if(!categoryField.getText().toString().isEmpty()){
                        newCategory.setCategoryName(categoryField.getText().toString());
                        newCategory.setIsDeletable(1);

                        try{
                            DatabaseHelper.insertCategory(context, newCategory);
                            categoryField.setText("");

                            SpotDatabase database = Room.databaseBuilder(SaveSpotCategoryActivity.this.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                                    .allowMainThreadQueries() //DO NOT !!!
                                    .build();
                            int lastIdInsert = database.categoryDao().getLastRecordCategory();
                            database.close();
                            newCategory.setCategoryId(lastIdInsert);
                            adapter.insertCategory(newCategory);
                            Toast.makeText(context, "Category: " + newCategory.getCategoryName() + " has been created", Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception e){
                            Toast.makeText(context, "Could not save to database", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(context, "Category name is required", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }


    public void openViewSaveTitle(View view){
        Intent intent = new Intent(this, SaveTitleActivity.class);


        if(categoryToSave != null) {
            int catId = categoryToSave.getCategoryId();
            String catName = categoryToSave.getCategoryName();
            int catImg = categoryToSave.getCategoryImg();
            int catDel = categoryToSave.getIsDeletable();

            intent.putExtra("EXTRA_MESSAGE_CATEGORY_ID", catId);
            intent.putExtra("EXTRA_MESSAGE_CATEGORY_NAME", catName);
            intent.putExtra("EXTRA_MESSAGE_CATEGORY_IMG", catImg);
            intent.putExtra("EXTRA_MESSAGE_CATEGORY_IS_DELETABLE", catDel);
        }

        Coordinate coordinate = new Coordinate();
        coordinate.putExtraMessageCoordinate(intent, coordinatesToSave);

        startActivity(intent);
    }

}


package pesticides_map;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pesticides_map.DataLayer.Category;
import pesticides_map.DataLayer.Coordinate;
import pesticides_map.DataLayer.DatabaseHelper;
import pesticides_map.DataLayer.Spot;
import pesticides_map.DataLayer.SpotDatabase;
import com.example.arunn.silfraagri.R;

public class SaveTitleActivity extends AppCompatActivity {

    private List<String> exampleTitles = new ArrayList<String>();
    private String[] exampleStrings;
    private Category chosenCategory;
    private Context context;
    private String selectedTitle ="";
    private Spot spotToSave = new Spot();
    private Coordinate coordinates;

    public SaveTitleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_title);
        this.context = getApplicationContext();


        saveSpotButton();
        checkIncomingIntents();
        if (!this.chosenCategory.getCategoryName().isEmpty() && (this.chosenCategory.getCategoryName().equals("Insects") || this.chosenCategory.getCategoryName().equals("Fungus")
        || this.chosenCategory.getCategoryName().equals("Weeds") || this.chosenCategory.getCategoryName().equals("Animal Attack"))) {

            switch (chosenCategory.getCategoryName()) {
                case "Insects":
                    exampleStrings = getResources().getStringArray(R.array.titles_example_insects);
                    break;
                case "Fungus":
                    exampleStrings = getResources().getStringArray(R.array.titles_example_fungus);
                    break;
                case "Weeds":
                    exampleStrings = getResources().getStringArray(R.array.titles_example_weeds);
                    break;
                case "Animal Attack":
                    exampleStrings = getResources().getStringArray(R.array.titles_example_animal);
                    break;
                case "":
                    exampleStrings = new String[]{"Could not load titles"};
                    break;
            }
            for (String item : exampleStrings) {
                exampleTitles.add(item);
            }
        }
        else{
            exampleTitles.add("My Spot");
        }


        RecyclerView.Adapter adapter = new TitleRecyclerViewAdapter(exampleTitles);
        RecyclerView recyclerView = findViewById(R.id.title_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

    }


    private void checkIncomingIntents() {
        if (getIntent().hasExtra("EXTRA_MESSAGE_CATEGORY_ID") && getIntent().hasExtra("EXTRA_MESSAGE_CATEGORY_NAME") &&
                getIntent().hasExtra("EXTRA_MESSAGE_CATEGORY_IMG") && getIntent().hasExtra("EXTRA_MESSAGE_CATEGORY_IS_DELETABLE") && getIntent().hasExtra("EXTRA_MESSAGE_COORDINATES_LAT")
                && getIntent().hasExtra("EXTRA_MESSAGE_COORDINATES_LONG") && getIntent().hasExtra("EXTRA_MESSAGE_COORDINATES_LOCAL") && getIntent().hasExtra("EXTRA_MESSAGE_COORDINATES_COUNTRY")) {
            Bundle extra = getIntent().getExtras();


            int id = extra.getInt("EXTRA_MESSAGE_CATEGORY_ID");
            String name = extra.getString("EXTRA_MESSAGE_CATEGORY_NAME");
            int image = extra.getInt("EXTRA_MESSAGE_CATEGORY_IMG");
            int isDeletable = extra.getInt("EXTRA_MESSAGE_CATEGORY_IS_DELETABLE");
            this.chosenCategory = new Category(id, name, image, isDeletable);
            double latitude = extra.getDouble("EXTRA_MESSAGE_COORDINATES_LAT");
            double longitude = extra.getDouble("EXTRA_MESSAGE_COORDINATES_LONG");
            String locale = extra.getString("EXTRA_MESSAGE_COORDINATES_LOCAL");
            String country = extra.getString("EXTRA_MESSAGE_COORDINATES_COUNTRY");
            this.coordinates = new Coordinate(latitude ,longitude, locale, country);

        }
    }


    private void saveSpotButton() {

        ImageButton btn = this.findViewById(R.id.save_title_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView textView = (TextView) findViewById(R.id.editTitle);
                Boolean hasTitle = false;

                if(!(textView.getText().toString()).isEmpty() && !(textView.getText().toString().equals(" "))){

                    spotToSave.setSpotTitle(textView.getText().toString());
                    hasTitle = true;
                }
                else if(!selectedTitle.isEmpty()){

                    spotToSave.setSpotTitle(selectedTitle);
                    hasTitle = true;
                }
                else {

                    Toast.makeText(context, "You must choose a title for your Spot", Toast.LENGTH_LONG).show();
                }


                if(hasTitle) {
                    try {


                        DatabaseHelper.insertCoordinate(context, coordinates);


                        EditText editView = findViewById(R.id.addDescription);
                        String description = editView.getText().toString();

                        spotToSave.setSpotDescription(description);

                        spotToSave.setSpotCategory(chosenCategory.getCategoryId());

                        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class,"SpotDatabase")
                                .allowMainThreadQueries()
                                .build();
                        int coordinateToSave = database.coordinateDao().getLastRecordCoordinates();

                        database.close();
                        spotToSave.setSpotCoordinate(coordinateToSave);


                        DatabaseHelper.insertSpot(context, spotToSave);
                        Toast.makeText(context, spotToSave.getSpotTitle() + " has successfully been saved!", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(context, MapsStart.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        ActivityCompat.finishAffinity(SaveTitleActivity.this);
                        startActivity(intent);

                    } catch (Exception e) {

                        Toast.makeText(context, "Could not save Spot", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}


package pesticides_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.arunn.silfraagri.R;

public class MainMenuScreen extends AppCompatActivity {

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_screen);
        this.context = getApplicationContext();
        //Instansiera knappar
        openMySpots();
        openCategoryCollection();
    }

    /**
     * Öppna SpotCollectionActivity
     */
    private void openMySpots() {
        //Ny intent
        ImageButton btn = findViewById(R.id.my_spots_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpotCollectionActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Öppna CategoryCollectionActivity
     */
    private void openCategoryCollection(){
        ImageButton btn = findViewById(R.id.categories_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryCollectionActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Öppna EditSpotsActivity
     */
    private void openEditSpots(){

    }

}

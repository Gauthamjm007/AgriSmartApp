package SuggestionActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.arunn.silfraagri.R;

import java.util.ArrayList;
import java.util.List;

public class Suggestions extends AppCompatActivity {

    List<Suggestions_Data> CropList;
    ListView listView;
   // String strh,strph;
    int Humidity;
    float pH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        CropList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        //strh = getIntent().getExtras().getString("humidity");
        // strph = getIntent().getExtras().getString("ph");
        // Humidity = Integer.parseInt(strh);
        //pH = Float.parseFloat(strph);
        Humidity=70;
        pH=7;

        if((pH>=5.5 && pH<=7.0)&&(Humidity>=60 && Humidity<=80)){
            CropList.add(new Suggestions_Data(R.drawable.paddy, "Paddy", "Grain" ,1));
        }
         if(pH>=4.5 && pH<=8.0){
            CropList.add(new Suggestions_Data(R.drawable.ragi, "Ragi", "Grain", 2));
            }
         if((pH>=5.2 && pH<=7.0)&&(Humidity>=45 && Humidity<=65)){
            CropList.add(new Suggestions_Data(R.drawable.areca, "Areca", "Nut", 3));
        }
         if((pH>=6.0 && pH<=7.2)&&(Humidity>=50 && Humidity<=80)){
            CropList.add(new Suggestions_Data(R.drawable.maize, "Maize", "Grain", 4));
        }
         if(pH>=6.0 && pH<=7.5){
            CropList.add(new Suggestions_Data(R.drawable.sorghum, "Sorghum", "Grain", 5));
        }
         if((pH>=5.0 && pH<=8.5)&&(Humidity>=80 && Humidity<=85)){
            CropList.add(new Suggestions_Data(R.drawable.sugarcane, "Sugarcane", "", 6));
        }
         if((pH>=6.0 && pH<=7.5)&&(Humidity>=50 && Humidity<=70)){
            CropList.add(new Suggestions_Data(R.drawable.sunflower, "Sunflower", "Flower", 7));
        }
         if((pH>=6.5 && pH<=8.0)&&(Humidity>=30 && Humidity<=40)){
            CropList.add(new Suggestions_Data(R.drawable.cotton, "Cotton", "", 8));
        }
        else if((pH>=5.2 && pH<=6.8)&&(Humidity>=0 && Humidity<=60)){
            CropList.add(new Suggestions_Data(R.drawable.coconut, "Coconut", "Nut", 9));
        }
        if((pH>=6.0 && pH<=7.5)&&(Humidity>=70 && Humidity<=90)){
            CropList.add(new Suggestions_Data(R.drawable.coffee, "Coffee", "Seed", 10));
        }
         if((pH>=5.5 && pH<=7.0)&&(Humidity>=80 && Humidity<=90)){
            CropList.add(new Suggestions_Data(R.drawable.tea, "Tea", "Leaf", 11));
        }
        else if((pH>=5.5 && pH<=6.5)&&(Humidity>=75 && Humidity<=85)){
            CropList.add(new Suggestions_Data(R.drawable.banana, "Banana", "Fruit", 12));
        }
         if((pH>=5.5 && pH<=6.5)&&(Humidity>=70 && Humidity<=90)){
            CropList.add(new Suggestions_Data(R.drawable.ginger, "Ginger", "Herb", 13));
        }
         if((pH>=6.0 && pH<=6.5)&&(Humidity>=70 && Humidity<=90)){
            CropList.add(new Suggestions_Data(R.drawable.turmeric, "Turmeric", "Herb", 14));
        }

         if(pH>=6.5 && pH<=7.0){
            CropList.add(new Suggestions_Data(R.drawable.groundnut, "Groundnut", "Nut", 15));
        }
         if((pH>=4.5 && pH<=6.0)&&(Humidity>=60 && Humidity<=80)){
            CropList.add(new Suggestions_Data(R.drawable.rubber, "Rubber", "Plant", 16));
        }
         if(pH>=5.5 && pH<=6.5){
            CropList.add(new Suggestions_Data(R.drawable.potato, "Potato", "Vegitable", 17));
        }
         if((pH>=5.0 && pH<=6.5)&&(Humidity>=50 && Humidity<=70)){
            CropList.add(new Suggestions_Data(R.drawable.cashew, "Cashewnut", "Nut", 18));
        }
         if((pH>=5.5 && pH<=6.0)&&(Humidity>=60 && Humidity<=80)){
            CropList.add(new Suggestions_Data(R.drawable.brinjal, "Brinjal", "Vegitable", 19));
        }
         if((pH>=5.5 && pH<=6.5)&&(Humidity>=65 && Humidity<=75)){
            CropList.add(new Suggestions_Data(R.drawable.onion, "Onion", "Vegitable", 20));
        }
         if((pH>=5.0 && pH<=6.0)&&(Humidity>=50 && Humidity<=70)){
            CropList.add(new Suggestions_Data(R.drawable.chilli, "Chilli", "Vegitable", 21));
        }
         if((pH>=6.0 && pH<=6.8)&&(Humidity>=70 && Humidity<=90)){
            CropList.add(new Suggestions_Data(R.drawable.tomato, "Tomato", "Vegitable", 22));
        }
         if((pH>=5.5 && pH<=7.5)&&(Humidity>=50 && Humidity<=60)){
            CropList.add(new Suggestions_Data(R.drawable.cauliflower, "Cauliflower", "Vegitable", 23));
        }
         if((pH>=5.5 && pH<=7.0)&&(Humidity>=50 && Humidity<=70)){
            CropList.add(new Suggestions_Data(R.drawable.capsicum, "Capsicum", "Vegitable", 24));
        }
         if((pH>=5.5 && pH<=6.5)&&(Humidity>=50 && Humidity<=60)){
            CropList.add(new Suggestions_Data(R.drawable.cabbage, "Cabbage", "Vegitable", 25));
        }
         if ((pH>=6.0 && pH<=6.8)&&(Humidity>=40 && Humidity<=80)){
            CropList.add(new Suggestions_Data(R.drawable.watermelon, "Watermelon", "Fruit", 26));
        }
        if(4.5>pH || pH>8.5) {
            CropList.add(new Suggestions_Data(R.drawable.nocrops, "No crops", "", 27));
        }

        SuggestListAdapter adapter = new SuggestListAdapter(this, R.layout.my_custom_suggestion_list, CropList);

        listView.setAdapter(adapter);
    }
}
package fertilizer_darshan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arunn.silfraagri.R;

import java.util.ArrayList;
import java.util.List;

public class P_Page extends AppCompatActivity implements View.OnClickListener {

    public TextView inputnumber, pbutton;
    List<Fertilizer_Data> FList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p__page);
        FList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.plistView);
        inputnumber = (EditText) findViewById(R.id.inputnumber);
        pbutton = (TextView) findViewById(R.id.pbutton);
        pbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) findViewById(R.id.pbutton);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);

        String GetEditText = inputnumber.getText().toString();
        if (TextUtils.isEmpty(GetEditText)) {
            Toast.makeText(P_Page.this, "Please enter a number", Toast.LENGTH_LONG).show();
        } else {

            int number = Integer.parseInt(inputnumber.getText().toString());

            final int t3 = ((number * 100) / 1);

            final int t11 = ((number * 100) / 1);
            final int t12 = ((number * 100) / 6);

            final int t14 = ((number * 100) / 3);
            final int t15 = ((number * 100) / 1);
            final int t16 = ((number * 100) / 2);
            final int t17 = ((number * 100) / 2);
            final int t18 = ((number * 100) / 1);
            final int t19 = ((number * 100) / 2);
            final int t20 = ((number * 100) / 1);
            final int t21 = ((number * 100) / 2);
            final int t22 = ((number * 100) / 20);

            final int t24 = ((number * 100) / 5);
            final int t25 = ((number * 100) / 18);
            final int t26 = ((number * 100) / 33);
            final int t27 = ((number * 100) / 47);
            final int t28 = ((number * 100) / 5);
            final int t29 = ((number * 100) / 37);
            final int t30 = ((number * 100) / 23);


            String s3 = Integer.toString(t3);

            String s11 = Integer.toString(t11);
            String s12 = Integer.toString(t12);

            String s14 = Integer.toString(t14);
            String s15 = Integer.toString(t15);
            String s16 = Integer.toString(t16);
            String s17 = Integer.toString(t17);
            String s18 = Integer.toString(t18);
            String s19 = Integer.toString(t19);
            String s20 = Integer.toString(t20);
            String s21 = Integer.toString(t21);
            String s22 = Integer.toString(t22);

            String s24 = Integer.toString(t24);
            String s25 = Integer.toString(t25);
            String s26 = Integer.toString(t26);
            String s27 = Integer.toString(t27);
            String s28 = Integer.toString(t28);
            String s29 = Integer.toString(t29);
            String s30 = Integer.toString(t30);

            FList.clear();
            FertilizerListAdapter adapter = new FertilizerListAdapter(this, R.layout.activity_my_custom_fertilizer_list, FList);
            adapter.notifyDataSetChanged();


            FList.add(new Fertilizer_Data(R.drawable.cow_manure, "Cow Manure", s3 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.alfalfa, "Alfalfa Meal", s11 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bat ,"Bat Guano", s12 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.cottonseed_meal1, "Cotton Seed Meal", s14 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.corn_gluten_meal, "Corn Gluten Meal", s15 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.seaweed_fertilizer, "Seaweed", s16 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.chicken_manure, "Chicken Manure", s17 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.greensand, "GreenSand", s18 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.compost, "Compost", s19 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.soybean_meal, "Soybean Meal", s20 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bloodmeal, "Blood Meal", s21 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bonemeal, "Bone Meal", s22 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.fis_hmeal, "Fish Meal", s24 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.single_superphosphate, "Single Superphosphate ", s25 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.double_superphosphate, "Double Superphostate", s26 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.triple_superphosphate, "Triple Superphosphate", s27 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.basic_slag, "Basic Slage", s28 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.dicalcium_phosphate, "Diacalcium Phosphate", s29 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.rock_phosphate, "Rock Phosphate", s30 + " Kg/ha"));


            listView.setAdapter(adapter);
        }
    }
}

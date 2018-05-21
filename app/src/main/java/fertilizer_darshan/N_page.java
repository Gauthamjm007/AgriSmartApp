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

public class N_page extends AppCompatActivity implements View.OnClickListener {

    public TextView inputnumber, okbutton;
    List<Fertilizer_Data> FList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_page);
        FList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.nlistView);
        inputnumber = (EditText) findViewById(R.id.inputnumber);
        okbutton = (TextView) findViewById(R.id.okbutton);
        okbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button button = (Button) findViewById(R.id.okbutton);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);


        String GetEditText = inputnumber.getText().toString();
        if (TextUtils.isEmpty(GetEditText)) {
            Toast.makeText(N_page.this, "Please enter a number", Toast.LENGTH_LONG).show();
        } else {
            int number = Integer.parseInt(inputnumber.getText().toString());
            final int t1 = ((number * 100) / 21);
            final int t2 = ((number * 100) / 45);
            final int t3 = ((number * 100) / 3);
            final int t4 = ((number * 100) / 25);
            final int t5 = ((number * 100) / 33);
            final int t6 = ((number * 100) / 3);
            final int t7 = ((number * 100) / 25);
            final int t8 = ((number * 100) / 16);
            final int t9 = ((number * 100) / 18);
            final int t10 = ((number * 100) / 212);
            final int t11 = ((number * 100) / 3);
            final int t12 = ((number * 100) / 8);
            final int t13 = ((number * 100) / 9);
            final int t14 = ((number * 100) / 6);
            final int t15 = ((number * 100) / 1);
            final int t16 = ((number * 100) / 1);
            final int t17 = ((number * 100) / 4);
            final int t18 = ((number * 100) / 1);
            final int t19 = ((number * 100) / 2);
            final int t20 = ((number * 100) / 3);
            final int t21 = ((number * 100) / 12);
            final int t22 = ((number * 100) / 4);
            final int t23 = ((number * 100) / 12);
            final int t24 = ((number * 100) / 10);

            String s1 = Integer.toString(t1);
            String s2 = Integer.toString(t2);
            String s3 = Integer.toString(t3);
            String s4 = Integer.toString(t4);
            String s5 = Integer.toString(t5);
            String s6 = Integer.toString(t6);
            String s7 = Integer.toString(t7);
            String s8 = Integer.toString(t8);
            String s9 = Integer.toString(t9);
            String s10 = Integer.toString(t10);
            String s11 = Integer.toString(t11);
            String s12 = Integer.toString(t12);
            String s13 = Integer.toString(t13);
            String s14 = Integer.toString(t14);
            String s15 = Integer.toString(t15);
            String s16 = Integer.toString(t16);
            String s17 = Integer.toString(t17);
            String s18 = Integer.toString(t18);
            String s19 = Integer.toString(t19);
            String s20 = Integer.toString(t20);
            String s21 = Integer.toString(t21);
            String s22 = Integer.toString(t22);
            String s23 = Integer.toString(t23);
            String s24 = Integer.toString(t24);

            FList.clear();
            FertilizerListAdapter adapter = new FertilizerListAdapter(this, R.layout.activity_my_custom_fertilizer_list, FList);
            adapter.notifyDataSetChanged();


            FList.add(new Fertilizer_Data(R.drawable.ammonium_sulfate, "Ammonium Sulphate", s1 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.urea, "Urea", s2 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.cow_manure, "Cow Manure", s3 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.ammonium_chloride, "Ammonium Chloride", s4 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.amm_nitrate, "Ammonium Nitrate", s5 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.amm_sulphate_nitrate, "Ammonium Sulphate Nitrate", s6 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.calcium_ammonium_nitrate, "Calcium Ammonium Nitrate", s7 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.sodium_nitrate,"Sodium Nitrate", s8 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.calcium_nitrate, "Calcium nitrate", s9 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.calcium_cyanamide, "Calcium Cynamide", s10 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.alfalfa, "Alfalfa Meal", s11 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bat, "Bat Guano", s12 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.fish_emulsion, "Fish emulsion", s13 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.cottonseed_meal1, "Cotton Seed Meal", s14 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.corn_gluten_meal, "Corn Gluten Meal", s15 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.seaweed_fertilizer, "Seaweed", s16 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.chicken_manure, "Chicken Manure", s17 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.greensand, "GreenSand", s18 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.compost, "Compost", s19 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.soybean_meal, "Soybean Meal", s20 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bloodmeal, "Blood Meal", s21 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.bonemeal, "Bone Meal", s22 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.feather_meal, "Feather Meal", s23 + " Kg/ha"));
            FList.add(new Fertilizer_Data(R.drawable.fis_hmeal, "Fish Meal", s24 + " Kg/ha"));


            listView.setAdapter(adapter);
        }
    }
}


package soil_package;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.arunn.silfraagri.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NPK_PieChart extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    String q1,q2,q3;
    float y1,y2,y3;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npk__pie_chart);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                q1 = dataSnapshot.child("sensors").child("NPK").child("N").getValue().toString();
                q2 = dataSnapshot.child("sensors").child("NPK").child("P").getValue().toString();
                q3 = dataSnapshot.child("sensors").child("NPK").child("K").getValue().toString();

                y1= Float.parseFloat(q1);
                y2= Float.parseFloat(q2);
                y3= Float.parseFloat(q3);


                pieChart = (PieChart) findViewById(R.id.idPieChart);
                pieChart.setUsePercentValues(false);
                pieChart.getDescription().setEnabled(false);
                pieChart.setExtraOffsets(5,10,5,5);
                pieChart.setDragDecelerationFrictionCoef(0.99f);

                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setHoleRadius(20f);
                pieChart.setTransparentCircleRadius(30f);

                ArrayList<PieEntry> yValues = new ArrayList<>();

                yValues.add(new PieEntry(y1,"Nitrogen"));
                yValues.add(new PieEntry(y2,"Phosphorous"));
                yValues.add(new PieEntry(y3,"Potassium"));

                Description description = new Description();
                description.setText("Nutrition Content of Soil");
                description.setTextSize(15);
                pieChart.setDescription(description);

                pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

                PieDataSet dataSet = new PieDataSet(yValues,"Nutrients in Kg/Hec");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                PieData data = new PieData((dataSet));
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.WHITE);
                pieChart.setData(data);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

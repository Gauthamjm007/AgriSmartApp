package SuggestionActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CropDetails extends AppCompatActivity {
    String name,paddy,n,p,k;
    ImageView cropimage;
    public TextView recomn,recomp,recomk,landn,landp,landk,requiren,requirep,requirek,cropname;
    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);
        cropimage = (ImageView) findViewById(R.id.cropimage);
        recomn = (TextView) findViewById(R.id.RecomN);
        recomp = (TextView) findViewById(R.id.RecomP);
        recomk = (TextView) findViewById(R.id.RecomK);
        landn = (TextView) findViewById(R.id.LandN);
        landp = (TextView) findViewById(R.id.LandP);
        landk = (TextView) findViewById(R.id.LandK);
        requiren = (TextView) findViewById(R.id.RequireN);
        requirep = (TextView) findViewById(R.id.RequireP);
        requirek = (TextView) findViewById(R.id.RequireK);
        cropname = (TextView) findViewById(R.id.CropName);
        name = getIntent().getExtras().getString("NAME");
        mRef= FirebaseDatabase.getInstance().getReference();


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n=dataSnapshot.child("sensors").child("npk").child("n").getValue().toString();
                p=dataSnapshot.child("sensors").child("npk").child("p").getValue().toString();
                k=dataSnapshot.child("sensors").child("npk").child("k").getValue().toString();


                if (name.equals("Paddy")) {

                    cropimage.setImageResource(R.drawable.paddy);
                    cropname.setText("Paddy");
                    recomn.setText("150");
                    recomp.setText("40-50");
                    recomk.setText("40-50");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 150 - ni;
                    int rp=45-pi;
                    int rk=45-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);

                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lpaddy)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());

                    }


                if (name.equals("Ragi")) {

                    cropimage.setImageResource(R.drawable.ragi);
                    cropname.setText("Ragi");
                    recomn.setText("50");
                    recomp.setText("50");
                    recomk.setText("50");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 50 - ni;
                    int rp=50-pi;
                    int rk=50-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lragi)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Areca")) {

                    cropimage.setImageResource(R.drawable.areca);
                    cropname.setText("Arecanut");
                    recomn.setText("100");
                    recomp.setText("40");
                    recomk.setText("140");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 100 - ni;
                    int rp=40-pi;
                    int rk=140-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lareca)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Maize")) {

                    cropimage.setImageResource(R.drawable.maize);
                    cropname.setText("Maize");
                    recomn.setText("150");
                    recomp.setText("60-75");
                    recomk.setText("30-40");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 150 - ni;
                    int rp=70-pi;
                    int rk=35-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lmaize)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Sorghum")) {

                    cropimage.setImageResource(R.drawable.sorghum);
                    cropname.setText("Sorghum");
                    recomn.setText("60");
                    recomp.setText("40");
                    recomk.setText("40");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 60 - ni;
                    int rp=40-pi;
                    int rk=40-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lsorghum)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Sugarcane")) {

                    cropimage.setImageResource(R.drawable.sugarcane);
                    cropname.setText("Sugarcane");
                    recomn.setText("250");
                    recomp.setText("100");
                    recomk.setText("125");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 250 - ni;
                    int rp=100-pi;
                    int rk=125-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lsugarcane)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Sunflower")) {

                    cropimage.setImageResource(R.drawable.sunflower);
                    cropname.setText("Sunflower");
                    recomn.setText("60");
                    recomp.setText("75");
                    recomk.setText("60");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 60 - ni;
                    int rp=75-pi;
                    int rk=60-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lsunflower)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Cotton")) {

                    cropimage.setImageResource(R.drawable.cotton);
                    cropname.setText("Cotton");
                    recomn.setText("120");
                    recomp.setText("60");
                    recomk.setText("60");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 120 - ni;
                    int rp=60-pi;
                    int rk=60-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcotton)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Coconut")) {

                    cropimage.setImageResource(R.drawable.coconut);
                    cropname.setText("Coconut");
                    recomn.setText("120");
                    recomp.setText("60");
                    recomk.setText("60");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 120 - ni;
                    int rp=60-pi;
                    int rk=60-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcoconut)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Coffee")) {

                    cropimage.setImageResource(R.drawable.coffee);
                    cropname.setText("Coffee");
                    recomn.setText("45");
                    recomp.setText("34");
                    recomk.setText("45");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 45 - ni;
                    int rp=34-pi;
                    int rk=45-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);

                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcoffee)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Tea")) {

                    cropimage.setImageResource(R.drawable.tea);
                    cropname.setText("Tea");
                    recomn.setText("300");
                    recomp.setText("100");
                    recomk.setText("300");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 300 - ni;
                    int rp=100-pi;
                    int rk=300-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.ltea)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Banana")) {

                    cropimage.setImageResource(R.drawable.banana);
                    cropname.setText("Tea");
                    recomn.setText("-");
                    recomp.setText("-");
                    recomk.setText("-");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);
                    requiren.setText("-");
                    requirep.setText("-");
                    requirek.setText("-");
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lbanana)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Ginger")) {

                    cropimage.setImageResource(R.drawable.ginger);
                    cropname.setText("Ginger");
                    recomn.setText("100");
                    recomp.setText("50");
                    recomk.setText("50");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 100 - ni;
                    int rp=50-pi;
                    int rk=50-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lginger)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Turmeric")) {

                    cropimage.setImageResource(R.drawable.turmeric);
                    cropname.setText("Turmeric");
                    recomn.setText("60");
                    recomp.setText("50");
                    recomk.setText("120");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 60 - ni;
                    int rp=50-pi;
                    int rk=120-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lturmeric)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Groundnut")) {

                    cropimage.setImageResource(R.drawable.groundnut);
                    cropname.setText("Groundnut");
                    recomn.setText("20");
                    recomp.setText("40");
                    recomk.setText("40");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 20 - ni;
                    int rp=40-pi;
                    int rk=40-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lgroundnut)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Rubber")) {

                    cropimage.setImageResource(R.drawable.rubber);
                    cropname.setText("Rubber");
                    recomn.setText("-");
                    recomp.setText("-");
                    recomk.setText("-");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);
                    requiren.setText("-");
                    requirep.setText("-");
                    requirek.setText("-");
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lrubber)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Potato")) {

                    cropimage.setImageResource(R.drawable.potato);
                    cropname.setText("Potato");
                    recomn.setText("80-100");
                    recomp.setText("80-100");
                    recomk.setText("100-120");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 90 - ni;
                    int rp=90-pi;
                    int rk=110-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lpotato)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Cashewnut")) {

                    cropimage.setImageResource(R.drawable.cashew);
                    cropname.setText("Cashewnut");
                    recomn.setText("280");
                    recomp.setText("160");
                    recomk.setText("240");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 280 - ni;
                    int rp=160-pi;
                    int rk=240-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcashewnut)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Brinjal")) {

                    cropimage.setImageResource(R.drawable.brinjal);
                    cropname.setText("Brinjal");
                    recomn.setText("150");
                    recomp.setText("150");
                    recomk.setText("100");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 150 - ni;
                    int rp=150-pi;
                    int rk=100-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lbrinjal)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Onion")) {

                    cropimage.setImageResource(R.drawable.onion);
                    cropname.setText("Onion");
                    recomn.setText("60");
                    recomp.setText("60");
                    recomk.setText("30");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 60 - ni;
                    int rp=60-pi;
                    int rk=30-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lonion)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Chilli")) {

                    cropimage.setImageResource(R.drawable.chilli);
                    cropname.setText("Chilli");
                    recomn.setText("75");
                    recomp.setText("25");
                    recomk.setText("25");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 75 - ni;
                    int rp=25-pi;
                    int rk=25-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lchilli)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Tomato")) {

                    cropimage.setImageResource(R.drawable.tomato);
                    cropname.setText("Tomato");
                    recomn.setText("200");
                    recomp.setText("65");
                    recomk.setText("250");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 200 - ni;
                    int rp=65-pi;
                    int rk=250-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.tomato)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }


                if (name.equals("Cauliflower")) {

                    cropimage.setImageResource(R.drawable.cauliflower);
                    cropname.setText("Cauliflower");
                    recomn.setText("90");
                    recomp.setText("90");
                    recomk.setText("90");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 90 - ni;
                    int rp=90-pi;
                    int rk=90-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcauliflower)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Capsicum")) {

                    cropimage.setImageResource(R.drawable.capsicum);
                    cropname.setText("Capsicum");
                    recomn.setText("40");
                    recomp.setText("60");
                    recomk.setText("30");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 40 - ni;
                    int rp=60-pi;
                    int rk=30-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcapsicum)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Cabbage")) {

                    cropimage.setImageResource(R.drawable.cabbage);
                    cropname.setText("Cabbage");
                    recomn.setText("90");
                    recomp.setText("90");
                    recomk.setText("90");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 90 - ni;
                    int rp=90-pi;
                    int rk=90-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lcabbage)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                if (name.equals("Watermelon")) {

                    cropimage.setImageResource(R.drawable.watermelon);
                    cropname.setText("Watermelon");
                    recomn.setText("200");
                    recomp.setText("100");
                    recomk.setText("100");
                    landn.setText(n);
                    landp.setText(p);
                    landk.setText(k);

                    int ni=Integer.parseInt(n);
                    int pi=Integer.parseInt(p);
                    int ki=Integer.parseInt(k);
                    int rn= 200 - ni;
                    int rp=100-pi;
                    int rk=100-ki;
                    String srn =Integer.toString(rn);
                    String srp =Integer.toString(rp);
                    String srk =Integer.toString(rk);

                    requiren.setText(srn);
                    requirep.setText(srp);
                    requirek.setText(srk);
                    ((TextView) findViewById(R.id.link)).setText(Html.fromHtml(getResources().getString(R.string.lwatermelon)));
                    ((TextView) findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

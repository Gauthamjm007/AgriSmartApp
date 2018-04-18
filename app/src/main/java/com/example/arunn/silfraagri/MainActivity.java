package com.example.arunn.silfraagri;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import bottom_navigation_package.InfoFragment;
import bottom_navigation_package.NotificationFragment;
import bottom_navigation_package.SoilFragment;
import bottom_navigation_package.SuggestionFragment;
import bottom_navigation_package.WeatherFragment;
import de.hdodenhof.circleimageview.CircleImageView;
import info_bar_package.MapsActivity;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar mToolbar;
    private CircleImageView NavProfileImage;
    private TextView NavProfileUserName;
    private FirebaseAuth mAuth;
    private DatabaseReference UserRef;
    String currentUserId;
    BottomNavigationView bottomNavigationView;
    TextView textview;
    FirebaseDatabase firebaseDatabase;
    NotificationBadge mbadge;
    private static final String TAG = "MainActivity";
    Integer Stat,soilmoisture;
    float  gspeed,soilph;
    String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,StatuS,gpsspeed,Soilmoisture,Soilph,ncount;

    Ndbhelper ndbhelper;
    SQLiteDatabase sqLiteDatabase;
    Context context=this;
    private int count;
    NotificationCompat.Builder notification;
    private static final int uniqueid=11111;



    public DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();



        mAuth=(FirebaseAuth.getInstance());
        currentUserId=mAuth.getCurrentUser().getUid();
        UserRef= FirebaseDatabase.getInstance().getReference().child("Users");

        mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawable_layout);
        actionBarDrawerToggle= new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=(NavigationView) findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        NavProfileImage=(CircleImageView)navView.findViewById(R.id.nav_profile_image);
        NavProfileUserName=(TextView)navView.findViewById(R.id.nav_user_full_name);

        setTitle("Notification Fragment"); //this will set title of Action Bar
        NotificationFragment notificationfragment = new NotificationFragment();
        android.support.v4.app.FragmentTransaction notificationfragmentTransaction = getSupportFragmentManager().beginTransaction();
        notificationfragmentTransaction.replace(R.id.frag, notificationfragment, "Notification Fragment");
        notificationfragmentTransaction.commit();


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                StatuS = dataSnapshot.child("sensors").child("battery").getValue().toString();
                Stat = Integer.parseInt(StatuS);
                switch (Stat){
                    case 5:

                        notification.setSmallIcon(R.drawable.ic_launcher);
                        notification.setTicker("AgriSmart");
                        notification.setWhen(System.currentTimeMillis());
                        notification.setContentTitle("AgriSmart Low Battery");
                        notification.setContentText("Your AgriSmart device Battery is Running Low");


                        String Date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        String Time= java.text.SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                        String Notification1="The battery in your Device is low and the device may turn itself off any time." +
                                " please charge the battery as soon as possible.                         " +
                                "                   Date: "+Date+" Time: "+Time;


                        ndbhelper=new Ndbhelper(context);
                        sqLiteDatabase=ndbhelper.getWritableDatabase();
                        Ndbhelper.addinformations(Notification1,Date,Time,sqLiteDatabase);
                        ndbhelper.close();

                        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueid,notification.build());

                        AlertDialog.Builder a_builder=new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Data is no longer accessible. Please recharge your Battery")
                                .setCancelable(false)
                                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Battery Low Alert !");
                        alert.show();


                        try{
                            String Msg;
                            FileInputStream fileInputStream = openFileInput("NotificationCount");
                            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            while((Msg=bufferedReader.readLine())!=null)
                            {
                                stringBuffer.append(Msg +"\n");
                                count=Integer.parseInt(Msg);

                            }
                            fileInputStream.close();
                            Msg=stringBuffer.toString();
                            bufferedReader.close();
                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }




                        count++;

                        String Message =Integer.toString(count);
                        String file_name="NotificationCount";
                        try{
                            FileOutputStream fileOutputStream=openFileOutput(file_name,MODE_PRIVATE);
                            fileOutputStream.write(Message.getBytes());
                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                }






                gpsspeed = dataSnapshot.child("sensors").child("gps").child("speed").getValue().toString();
                gspeed = Float.parseFloat(gpsspeed);
                if(gspeed>=10 && gspeed<=1000)
                {
                    String Date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String Time= java.text.SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                    String Notification2="Your device location is changed. Date:   " +
                            "                                    "+Date+" Time: "+Time;
                    ndbhelper=new Ndbhelper(context);
                    sqLiteDatabase=ndbhelper.getWritableDatabase();
                    Ndbhelper.addinformations(Notification2,Date,Time,sqLiteDatabase);
                    ndbhelper.close();

                    AlertDialog.Builder b_builder=new AlertDialog.Builder(MainActivity.this);
                    b_builder.setMessage("Agrismart Device Location is Changed,Please click below to find location on Map")
                            .setCancelable(false)
                            .setPositiveButton("Show Location", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, MapsActivity. class);
                                    intent.putExtra("LOCVAL1",d);
                                    intent.putExtra("LOCVAL2",e);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = b_builder.create();
                    alert.setTitle("Location Changed Alert !");
                    alert.show();


                    try{
                        String Msg;
                        FileInputStream fileInputStream = openFileInput("NotificationCount");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        while((Msg=bufferedReader.readLine())!=null)
                        {
                            stringBuffer.append(Msg +"\n");
                            count=Integer.parseInt(Msg);

                        }
                        fileInputStream.close();
                        Msg=stringBuffer.toString();
                        bufferedReader.close();
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }




                    count++;

                    String Message =Integer.toString(count);
                    String file_name="NotificationCount";
                    try{
                        FileOutputStream fileOutputStream=openFileOutput(file_name,MODE_PRIVATE);
                        fileOutputStream.write(Message.getBytes());
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }







                Soilmoisture = dataSnapshot.child("sensors").child("soil").child("soil_moisture").getValue().toString();
                soilmoisture = Integer.parseInt(Soilmoisture);
                if(soilmoisture>=800 && soilmoisture<=1024)
                {
                    String Date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String Time= java.text.SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                    String Notification3="Water content in a soil is less,Kindly water your plant as soon as possible.                                                                                                      Date: "+Date+" Time "+Time;

                    ndbhelper=new Ndbhelper(context);
                    sqLiteDatabase=ndbhelper.getWritableDatabase();
                    Ndbhelper.addinformations(Notification3,Date,Time,sqLiteDatabase);
                    ndbhelper.close();



                    try{
                        String Msg;
                        FileInputStream fileInputStream = openFileInput("NotificationCount");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        while((Msg=bufferedReader.readLine())!=null)
                        {
                            stringBuffer.append(Msg +"\n");
                            count=Integer.parseInt(Msg);

                        }
                        fileInputStream.close();
                        Msg=stringBuffer.toString();
                        bufferedReader.close();
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }




                    count++;

                    String Message =Integer.toString(count);
                    String file_name="NotificationCount";
                    try{
                        FileOutputStream fileOutputStream=openFileOutput(file_name,MODE_PRIVATE);
                        fileOutputStream.write(Message.getBytes());
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }






                Soilph= dataSnapshot.child("sensors").child("soil").child("pH").getValue().toString();
                soilph = Float.parseFloat(Soilph);
                if (soilph < 5 || soilph > 8) {

                    String Date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String Time = SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                    String Notification4 = "Soil pH is changed to " + Soilph +
                            ".Soil pH is a measure of the acidity and alkalinity in soils.                                                                                       Date: " + Date + " Time: " + Time;

                    ndbhelper = new Ndbhelper(context);
                    sqLiteDatabase = ndbhelper.getWritableDatabase();
                    Ndbhelper.addinformations(Notification4, Date, Time, sqLiteDatabase);
                    ndbhelper.close();


                    try {
                        String Msg;
                        FileInputStream fileInputStream = openFileInput("NotificationCount");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((Msg = bufferedReader.readLine()) != null) {
                            stringBuffer.append(Msg + "\n");
                            count = Integer.parseInt(Msg);

                        }
                        fileInputStream.close();
                        Msg = stringBuffer.toString();
                        bufferedReader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    count++;


                    String Message = Integer.toString(count);
                    String file_name = "NotificationCount";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(Message.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else { }






                try {
                    String Msg;
                    FileInputStream fileInputStream = openFileInput("NotificationCount");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((Msg = bufferedReader.readLine()) != null) {
                        stringBuffer.append(Msg + "\n");
                        count = Integer.parseInt(Msg);

                    }
                    fileInputStream.close();
                    Msg = stringBuffer.toString();
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        UserRef.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if (dataSnapshot.hasChild("fullname")){
                        String fullname= dataSnapshot.child("fullname").getValue().toString();
                        NavProfileUserName.setText(fullname);

                    }

                    if(dataSnapshot.hasChild("profile Image")){
                        String image =dataSnapshot.child("profile Image").getValue().toString();
                        Picasso.with(MainActivity.this).load(image).placeholder(R.drawable.profile).into(NavProfileImage);
                    }

                    else{
                        Toast.makeText(MainActivity.this, "Profile name Do not Exist.....", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                d = dataSnapshot.child("sensors").child("gps").child("lattitude").getValue().toString();
                e = dataSnapshot.child("sensors").child("gps").child("longitude").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                UserMenuSelector(item);

                return false;
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser == null){
            SendUserTologinActivity();
        }
        else{
            CheckUserExitence();
        }
    }

    private void CheckUserExitence() {
        final String current_user_ID=mAuth.getCurrentUser().getUid();
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_user_ID)){

                    SendUserToSetupActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void SendUserToSetupActivity() {

             Intent SetupIntent = new Intent(MainActivity.this, SetupActivity.class);
        SetupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(SetupIntent);
        finish();
    }

    private void SendUserTologinActivity() {

        Intent loginIntent = new Intent(MainActivity.this, loginactivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about_us:
                Toast.makeText(this,"About Us",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_contact:
                Toast.makeText(this,"Contact",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                mAuth.signOut();
                SendUserTologinActivity();
                Toast.makeText(this,"Loggedout",Toast.LENGTH_SHORT).show();
                break;


        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.notifications_id:
                setTitle("Notification Fragment"); //this will set title of Action Bar
                NotificationFragment notificationfragment = new NotificationFragment();
                android.support.v4.app.FragmentTransaction notificationfragmentTransaction = getSupportFragmentManager().beginTransaction();
                notificationfragmentTransaction.replace(R.id.frag, notificationfragment, "Notification Fragment");
                notificationfragmentTransaction.commit();
                return true;
            case R.id.info_id:
                setTitle("Info Fragment"); //this will set title of Action Bar
                InfoFragment infofragment = new InfoFragment();
                android.support.v4.app.FragmentTransaction infofragmentTransaction = getSupportFragmentManager().beginTransaction();
                infofragmentTransaction.replace(R.id.frag, infofragment, "Info Fragment");
                infofragmentTransaction.commit();
                return true;
            case R.id.weather_id:
                setTitle("weather Fragment"); //this will set title of Action Bar
                WeatherFragment weatherfragment = new WeatherFragment();
                android.support.v4.app.FragmentTransaction weatherfragmentTransaction = getSupportFragmentManager().beginTransaction();
                weatherfragmentTransaction.replace(R.id.frag, weatherfragment, "Weather Fragment");
                weatherfragmentTransaction.commit();
                return true;
            case R.id.soil_info_id:
                setTitle("Soil Info Fragment"); //this will set title of Action Bar
                SoilFragment soilfragment = new SoilFragment();
                android.support.v4.app.FragmentTransaction soilfragmentTransaction = getSupportFragmentManager().beginTransaction();
                soilfragmentTransaction.replace(R.id.frag, soilfragment, "Soil Fragment");
                soilfragmentTransaction.commit();
                return true;
            case R.id.suggestion_id:
                setTitle("SuggestionFragment"); //this will set title of Action Bar
                SuggestionFragment suggestionfragment = new SuggestionFragment();
                android.support.v4.app.FragmentTransaction suggestionfragmentTransaction = getSupportFragmentManager().beginTransaction();
                suggestionfragmentTransaction.replace(R.id.frag, suggestionfragment, "Suggestion Fragment");
                suggestionfragmentTransaction.commit();
                return true;
        }
        return true;
    }
}

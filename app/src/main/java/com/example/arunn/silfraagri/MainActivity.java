package com.example.arunn.silfraagri;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.RecoverySystem;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth=(FirebaseAuth.getInstance());
        currentUserId=mAuth.getCurrentUser().getUid();
        UserRef= FirebaseDatabase.getInstance().getReference().child("Users");

        mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


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

        UserRef.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if (dataSnapshot.hasChild("fullname")){
                        String fullname= dataSnapshot.child("fullname").getValue().toString();
                        NavProfileUserName.setText(fullname);

                    }

                    if(dataSnapshot.hasChild("profile mage")){
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
                infofragmentTransaction.replace(R.id.frag, infofragment, "Notification Fragment");
                infofragmentTransaction.commit();
                return true;
            case R.id.weather_id:
                setTitle("weather Fragment"); //this will set title of Action Bar
                WeatherFragment weatherfragment = new WeatherFragment();
                android.support.v4.app.FragmentTransaction weatherfragmentTransaction = getSupportFragmentManager().beginTransaction();
                weatherfragmentTransaction.replace(R.id.frag, weatherfragment, "Notification Fragment");
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
                suggestionfragmentTransaction.replace(R.id.frag, suggestionfragment, "Soil Fragment");
                suggestionfragmentTransaction.commit();
                return true;
        }
        return true;
    }
}

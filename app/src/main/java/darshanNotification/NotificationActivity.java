package darshanNotification;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arunn.silfraagri.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

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


public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView date,ph,time,notif;

    FirebaseDatabase firebaseDatabase;
    public DatabaseReference mRef;
    NotificationBadge mbadge;

    Ndbhelper ndbhelper;
    SQLiteDatabase sqLiteDatabase;
    Context context=this;
    private int count;

    private static final String TAG = "NotificationActivity";
    Integer Stat,soilmoisture;
    float  gspeed,soilph;
    String a,d,e,f,h,i,n,p,q,r,s,t,u,StatuS,gpsspeed,Soilmoisture,Soilph;

    NotificationCompat.Builder notification;
    private static final int uniqueid=11111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        mbadge=(NotificationBadge) findViewById(R.id.badge);
        notif =(Button) findViewById(R.id.notif);
        notif.setOnClickListener(this);


        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                d = dataSnapshot.child("sensors").child("gps").child("lattitude").getValue().toString();
                e = dataSnapshot.child("sensors").child("gps").child("longitude").getValue().toString();
                f = dataSnapshot.child("sensors").child("gps").child("speed").getValue().toString();
                h = dataSnapshot.child("sensors").child("soil").child("pH").getValue().toString();
                i = dataSnapshot.child("sensors").child("soil").child("soil_moisture").getValue().toString();

                q = dataSnapshot.child("sensors").child("battery").getValue().toString();

                s=dataSnapshot.child("sensors").child("npk").child("n").getValue().toString();
                t=dataSnapshot.child("sensors").child("npk").child("p").getValue().toString();
                u=dataSnapshot.child("sensors").child("npk").child("k").getValue().toString();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef= FirebaseDatabase.getInstance().getReference();
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
                        String Time= SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                        String Notification1="The battery in your Device is low and the device may turn itself off any time." +
                                " please charge the battery as soon as possible.                         " +
                                "                   Date: "+Date+" Time: "+Time;


                        ndbhelper=new Ndbhelper(context);
                        sqLiteDatabase=ndbhelper.getWritableDatabase();
                        Ndbhelper.addinformations(Notification1,Date,Time,sqLiteDatabase);
                        ndbhelper.close();

                        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueid,notification.build());

                        AlertDialog.Builder a_builder=new AlertDialog.Builder(NotificationActivity.this);
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
                    String Time= SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
                    String Notification2="Your device location is changed. Date:   " +
                            "                                    "+Date+" Time: "+Time;
                    ndbhelper=new Ndbhelper(context);
                    sqLiteDatabase=ndbhelper.getWritableDatabase();
                    Ndbhelper.addinformations(Notification2,Date,Time,sqLiteDatabase);
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







                Soilmoisture = dataSnapshot.child("sensors").child("soil").child("soil_moisture").getValue().toString();
               soilmoisture = Integer.parseInt(Soilmoisture);
                if(soilmoisture>=800 && soilmoisture<=1024)
                {
                    String Date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String Time= SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
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

                mbadge.setNumber(count);



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(NotificationActivity.this,notification. class);
        mbadge.setNumber(0);
        startActivity(intent);

        }

    }
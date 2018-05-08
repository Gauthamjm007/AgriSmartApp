package darshanNotification;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.arunn.silfraagri.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class notification extends AppCompatActivity {
    String str;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    Ndbhelper ndbhelper;
    Cursor cursor;
    Context context=this;
    private int ncount=0;

    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        listView = (ListView)findViewById(R.id.list_view);


        String Message =Integer.toString(ncount);
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



        listDataAdapter = new ListDataAdapter((getApplicationContext()),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        ndbhelper = new Ndbhelper(getApplicationContext());
        sqLiteDatabase = ndbhelper.getReadableDatabase();
        cursor=ndbhelper.getinformations(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do{
                String n_message,n_date,n_time;
                n_message=cursor.getString(0);
                n_date=cursor.getString(1);
                n_time=cursor.getString(2);

                Ndataprovider dataprovider = new Ndataprovider(n_message,n_date,n_time);
               listDataAdapter.add(dataprovider);
            }while(cursor.moveToNext());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nclear:
                Ndbhelper.deleteinformations(sqLiteDatabase);
                recreate();
                ndbhelper.close();
        }

        return super.onOptionsItemSelected(item);
    }

}

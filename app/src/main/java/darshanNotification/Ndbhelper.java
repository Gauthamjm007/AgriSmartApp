package darshanNotification;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static darshanNotification.Usercontract.Newuserinfo.N_DATE;
import static darshanNotification.Usercontract.Newuserinfo.N_TIME;
import static darshanNotification.Usercontract.Newuserinfo.TABLE_NAME;

public class Ndbhelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="USERINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+ Usercontract.Newuserinfo.TABLE_NAME+"("+ Usercontract.Newuserinfo.N_MESSAGE+" TEXT,"+
                    Usercontract.Newuserinfo.N_DATE+" TEXT,"+ N_TIME+" TEXT);";

    public Ndbhelper(Context context)

    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE_OPERATIONS","Database created/opened...");


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE_OPERATIONS","Table created...");

    }

    public static void addinformations(String n_message,String n_date,String n_time,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Usercontract.Newuserinfo.N_MESSAGE,n_message);
        contentValues.put(Usercontract.Newuserinfo.N_DATE,n_date);
        contentValues.put(Usercontract.Newuserinfo.N_TIME,n_time);
        db.insert(Usercontract.Newuserinfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE_OPERATIONS","One row is inserted...");

    }



public static Cursor getinformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projection = {Usercontract.Newuserinfo.N_MESSAGE,Usercontract.Newuserinfo.N_DATE,Usercontract.Newuserinfo.N_TIME};
        cursor=db.query(Usercontract.Newuserinfo.TABLE_NAME,projection,null,null,null,null, N_DATE+" DESC,"+ N_TIME +" DESC");
        return cursor;
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void deleteinformations(SQLiteDatabase db){
     Cursor cursor;
     db.execSQL("delete from "+ TABLE_NAME);
    }
}

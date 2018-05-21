package pesticides_map.DataLayer;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;


@android.arch.persistence.room.Database(entities = {Coordinate.class, Spot.class, Category.class}, version = 1)
public abstract class SpotDatabase extends RoomDatabase {

    private static SpotDatabase INSTANCE;

    public abstract Coordinate.CoordinateDao coordinateDao();
    public abstract Spot.SpotDao spotDao();
    public abstract Category.CategoryDao categoryDao();


    public static SpotDatabase getDatabase(Context context) {
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")


                            .allowMainThreadQueries()
                            .build();
        }

        return INSTANCE;
    }

    private static SpotDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                SpotDatabase.class,
                "my-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getDatabase(context).categoryDao().insertCategories(Category.populateData());
                            }
                        });
                    }
                })
                .build();
    }


    public static boolean checkDatabase(){

        SQLiteDatabase checkDB = null;
        try{
            checkDB = SQLiteDatabase.openDatabase("/data/data/sup.savemeaspot/databases/SpotDatabase.db",null,SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        }
        catch(SQLiteException e){

        }
        return checkDB != null;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}


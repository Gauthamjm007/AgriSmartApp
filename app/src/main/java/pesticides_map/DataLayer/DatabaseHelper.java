package pesticides_map.DataLayer;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;



public class DatabaseHelper {


    public DatabaseHelper() {

    }


    public static void populateDatabaseWithCategories(final Context context) {
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        if (database.categoryDao().getAllCategories().isEmpty()) {
            database.categoryDao().insertCategories(Category.populateData());
            database.close();
        }
        database.close();
    }


    public static void insertCategory(final Context context, Category category) {
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        database.categoryDao().insertCategory(category);
        database.close();
    }


    public static void insertSpot(final Context context, Spot spot){
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        database.spotDao().insertSingleSpot(spot);
        database.close();
    }


    public static void insertCoordinate(final Context context, Coordinate coordinate){
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        database.coordinateDao().insertSingleCoordinate(coordinate);
        database.close();
    }


    public static boolean checkIfSpotsExist(final Context context){
        boolean result = false;
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        int totalSpots = database.spotDao().countSpots();
        database.close();
        if(totalSpots >= 1)
        {
            result = true;
        }
        return result;
    }


    public static Coordinate getSpotCoordinates(Context context,Spot spot){
        Coordinate coordinate = new Coordinate();
        int spotCoordinate = spot.getSpotCoordinate();
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        coordinate = database.coordinateDao().getCoordinateByID(spotCoordinate);
        database.close();
        return coordinate;
    }


    public static List<Spot> getAllSpots(Context context) {
        List<Spot> spotList;
        if(checkIfSpotsExist(context)){
            SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                    .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                    .build();
            spotList = database.spotDao().getAllSpots();
            database.close();
            return spotList;
        }
        else{
            return null;
        }

    }


    public static void editSpot(Context context, Spot spot, String title, String description, Category category){

        Spot updatedSpot = spot;

        if(title != spot.getSpotTitle()){
            updatedSpot.setSpotTitle(title);
        }
        if(description != spot.getSpotDescription()){
            updatedSpot.setSpotDescription(description);
        }
        if(category.getCategoryId() != spot.getSpotCategory()) {
            updatedSpot.setSpotCategory(category.getCategoryId());
        }


        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(),SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries()
                .build();
        database.spotDao().updateSpotTitle(updatedSpot.getSpotTitle(),spot.getSpotId());
        database.spotDao().updateSpotCategory(updatedSpot.getSpotCategory(),spot.getSpotId());
        database.spotDao().updateSpotDescription(updatedSpot.getSpotDescription(),spot.getSpotId());
        database.close();

    }


    public static void deleteCategory(Context context, Category category){
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        database.categoryDao().deleteCategories(category);
        database.close();

    }


    public static void editCategory(Context context, Category category, String categoryName, int categoryImage){
        SpotDatabase database = Room.databaseBuilder(context.getApplicationContext(), SpotDatabase.class, "SpotDatabase")
                .allowMainThreadQueries() // // TODO: Skapa en asynkron metod för att köra köra queries mot databasen VIKTIGT!! Denna måste hanteras på en annan tråd i release-versionen!
                .build();
        database.categoryDao().editCategory(category.getCategoryId(), categoryName, categoryImage);

        database.close();
    }

}

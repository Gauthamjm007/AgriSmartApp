package pesticides_map.DataLayer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

import java.util.List;



@Entity(tableName = "Spot")
public class Spot {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "spotid")
    private int SpotId;
    @ColumnInfo(name = "spot_title")
    private String spotTitle;
    @ColumnInfo(name = "spot_description")
    private String spotDescription;
    @ColumnInfo(name ="spot_category")
    private int spotCategory;
    @ColumnInfo(name = "spot_coordinate")
    private int spotCoordinate;


    public int getSpotId(){return this.SpotId;}
    public void setSpotId(int spotId){ this.SpotId = spotId; }
    public String getSpotTitle(){
        return this.spotTitle;
    }
    public void setSpotTitle(String title){
        this.spotTitle = title;
    }
    public String getSpotDescription(){
        return this.spotDescription;
    }
    public void setSpotDescription(String desc){
        this.spotDescription = desc;
    }
    public int getSpotCategory(){return this.spotCategory;}
    public void setSpotCategory(int spotCat){this.spotCategory = spotCat;}
    public int getSpotCoordinate(){return this.spotCoordinate;}
    public void setSpotCoordinate(int spotCoord){this.spotCoordinate = spotCoord;}

    public Spot(){
    }

    @Dao
    public interface SpotDao{

        @Query("SELECT * FROM SPOT;")
        List<Spot> getAllSpots();


        @Query("SELECT * FROM SPOT WHERE SPOTID =(SELECT MAX(SPOTID) FROM SPOT)")
        Spot getSpotLast();


        @Query("SELECT COUNT(SPOTID) FROM SPOT")
        int countSpots();

        @Query("SELECT * FROM SPOT WHERE SPOT_TITLE LIKE :title")
        List<Spot> getSpotByTitle(String title);

        @Query("SELECT * FROM SPOT WHERE SPOT_CATEGORY LIKE :categoryId")
        List<Spot> getSpotsByCategory(int categoryId);


        @Query("UPDATE SPOT SET SPOT_TITLE = :spotTitle WHERE SPOTID = :spotId")
        void updateSpotTitle(String spotTitle, int spotId);


        @Query("UPDATE SPOT SET SPOT_DESCRIPTION = :spotDescription WHERE SPOTID = :spotId")
        void updateSpotDescription(String spotDescription, int spotId);


        @Query("UPDATE SPOT SET SPOT_CATEGORY = :categoryId WHERE SPOTID = :spotId")
        void updateSpotCategory(int categoryId, int spotId);


        @Insert (onConflict = OnConflictStrategy.IGNORE)
        void insertNewSpots(Spot... spots);

        @Insert (onConflict = OnConflictStrategy.IGNORE)
        void insertSingleSpot(Spot spot);


    }
}


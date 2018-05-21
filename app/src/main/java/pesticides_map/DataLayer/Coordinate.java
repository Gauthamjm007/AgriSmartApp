package pesticides_map.DataLayer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.Intent;

import java.util.List;



@Entity(tableName = "Coordinate")
public class Coordinate {
    @PrimaryKey(autoGenerate = true)
    private int coordinateId;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "local_address")
    private String localAddress;
    @ColumnInfo(name = "country")
    private String countryName;


    public Coordinate(){
    }


    public Coordinate(double latitude, double longitude, String locale, String country){
        this.latitude = latitude;
        this.longitude = longitude;
        this.localAddress = locale;
        this.countryName = country;
    }


    public Intent putExtraMessageCoordinate(Intent intent, Coordinate coordinate){
        intent.putExtra("EXTRA_MESSAGE_COORDINATES_LAT", coordinate.getLatitude());
        intent.putExtra("EXTRA_MESSAGE_COORDINATES_LONG", coordinate.getLongitude());
        intent.putExtra("EXTRA_MESSAGE_COORDINATES_LOCAL", coordinate.getLocalAddress());
        intent.putExtra("EXTRA_MESSAGE_COORDINATES_COUNTRY", coordinate.getCountryName());
        return intent;
    }


    public int getCoordinateId () {return this.coordinateId;}
    public void setCoordinateId(int coordId) { this.coordinateId = coordId; }
    public double getLatitude(){
        return this.latitude;
    }
    public void setLatitude(double lat){
        this.latitude = lat;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public void setLongitude(double lon){
        this.longitude = lon;
    }
    public String getLocalAddress(){
        return this.localAddress;
    }
    public void setLocalAddress(String address){
        this.localAddress = address;
    }
    public String getCountryName(){
        return this.countryName;
    }
    public void setCountryName(String country){
        this.countryName = country;
    }

    @Dao
    public interface CoordinateDao{

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insertCoordinates(Coordinate... coordinates);


        @Insert (onConflict = OnConflictStrategy.IGNORE)
        void insertSingleCoordinate(Coordinate coordinate);


        @Insert (onConflict = OnConflictStrategy.REPLACE)
        void insertDuplicateCoordinate(Coordinate... coordinates);


        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateCoordinate(Coordinate coordinate);


        @Delete()
        void deleteCoordinate(Coordinate coordinate);


        @Query("SELECT CoordinateID FROM COORDINATE WHERE CoordinateID =(SELECT MAX(CoordinateID) FROM COORDINATE)")
        int getLastRecordCoordinates();


        @Query("SELECT * FROM COORDINATE")
        List<Coordinate> getAllCoordinates();


        @Query("SELECT * FROM COORDINATE WHERE CoordinateId LIKE :spotCoordinate")
        Coordinate getCoordinateByID(int spotCoordinate);


        @Query("SELECT * FROM COORDINATE WHERE latitude LIKE :spotLatitude AND longitude LIKE :spotLongitude")
        List<Coordinate> getCoordinateByLatAndLong(double spotLatitude, double spotLongitude);


        @Query("SELECT * FROM COORDINATE WHERE country LIKE :spotCountry")
        List<Coordinate> getCoordinateByCountry(String spotCountry);


        @Query("SELECT * FROM COORDINATE WHERE local_address LIKE :spotAddress")
        List<Coordinate> getCoordinateByAddress(String spotAddress);


        @Query("DELETE FROM COORDINATE WHERE CoordinateID LIKE :coordinateId")
        void deleteCoordinates(int coordinateId);
    }

}

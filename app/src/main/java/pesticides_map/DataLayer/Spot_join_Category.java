package pesticides_map.DataLayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;


@Entity(tableName = "spot_join_category",  primaryKeys = {"category_id", "spot_in_cat"},
        foreignKeys = {
                @ForeignKey(entity = Category.class,
                        parentColumns = "category_id",
                        childColumns = "category_id"),
                @ForeignKey(entity = Spot.class,
                        parentColumns = "spot_id",
                        childColumns = "spot_in_cat")
        })
public class Spot_join_Category {

}

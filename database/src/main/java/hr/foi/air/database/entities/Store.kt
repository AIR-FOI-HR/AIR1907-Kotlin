package hr.foi.air.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class Store(
    @PrimaryKey(autoGenerate = true)
    var id:Int = -1,
    var name:String = "",
    var description:String = "",
    var imgUrl:String = "",
    var longitude:Long = -1,
    var latitude:Long = -1

)
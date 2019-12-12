package hr.foi.air.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
open class Store(@PrimaryKey(autoGenerate = true)
                 var id:Int?,
                 var name:String?,
                 var description:String?,
                 var imgUrl:String?,
                 var longitude:Long?,
                 var latitude:Long?){

    constructor(): this(null, null, null, null, null, null)
    constructor(store:Store):this(store.id, store.name, store.description, store.imgUrl, store.longitude, store.latitude)
}
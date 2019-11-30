package hr.foi.air.database.views

import androidx.room.DatabaseView

@DatabaseView("SELECT " +
        "discounts.id, " +
        "discounts.name, " +
        "discounts.description, " +
        "discounts.discountValue AS value, " +
        "stores.name AS storeName, " +
        "stores.latitude, " +
        "stores.longitude FROM discounts " +
        "INNER JOIN stores ON discounts.storeId = stores.id")
data class DiscountDetails(
    val id:Int?,
    val name:String?,
    val description:String?,
    val value:Int?,
    val storeName:String?,
    val latitude:Long?,
    val longitude:Long?
)
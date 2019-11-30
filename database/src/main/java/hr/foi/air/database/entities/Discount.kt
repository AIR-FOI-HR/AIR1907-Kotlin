package hr.foi.air.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "discounts")
data class Discount (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val storeId: Int,
    val name: String,
    val description: String,
    val discountValue: Int,
    val startDate: Date,
    val endDate: Date

    )
package hr.foi.air.database.entities

import androidx.room.*
import hr.foi.air.database.converters.DateConverter
import java.util.*

@Entity(tableName = "discounts")
@TypeConverters(DateConverter::class)
data class Discount (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ForeignKey(entity = Store::class, parentColumns = ["id"], childColumns = ["storeId"])
    @ColumnInfo(index = true)
    val storeId: Int,
    val name: String,
    val description: String,
    val discountValue: Int,
    val startDate: Date,
    val endDate: Date

    )
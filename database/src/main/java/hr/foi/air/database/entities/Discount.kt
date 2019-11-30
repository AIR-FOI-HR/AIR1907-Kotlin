package hr.foi.air.database.entities

import androidx.room.*
import hr.foi.air.database.converters.DateConverter
import java.util.*

@Entity(tableName = "discounts")
@TypeConverters(DateConverter::class)
data class Discount (
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    @ForeignKey(entity = Store::class, parentColumns = ["id"], childColumns = ["storeId"])
    @ColumnInfo(index = true)
    var storeId: Int = -1,
    var name: String = "",
    val description: String = "",
    var discountValue: Int = -1,
    val startDate: Date = Date(),
    val endDate: Date = Date()

    )
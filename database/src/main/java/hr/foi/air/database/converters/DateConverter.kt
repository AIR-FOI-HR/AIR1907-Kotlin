package hr.foi.air.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromDate(date:Date):Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp:Long):Date?{
        return if(timestamp==null)null else Date(timestamp)
    }
}
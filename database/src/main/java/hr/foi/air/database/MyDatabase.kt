package hr.foi.air.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = {}, views = {}, exportSchema = false)
public abstract class MyDatabase : RoomDatabase() {
}
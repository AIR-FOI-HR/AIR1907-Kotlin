package hr.foi.air.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.database.views.DiscountDetails

@Database(version = 1, entities = [Store::class, Discount::class], views = [DiscountDetails::class], exportSchema = false)
public abstract class MyDatabase : RoomDatabase() {
    companion object{
        private var INSTANCE:MyDatabase? = null

        fun getInstance(context: Context?): MyDatabase? {
            if(INSTANCE == null){
                synchronized(MyDatabase::class){
                    INSTANCE = Room.databaseBuilder(context?.applicationContext!!, MyDatabase::class.java, "main" ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
    public abstract fun getDao():DAO?
}
package hr.foi.air.database

import androidx.room.*
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStores(vararg stores:Store)
    @Update fun updateStores(vararg stores:Store)
    @Delete fun deleteStores(vararg stores:Store)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiscounts(vararg discount: Discount)
    @Update fun updateDiscounts(vararg discounts:Discount)
    @Delete fun deleteDiscounts(vararg discounts:Discount)

    @Query("SELECT * FROM stores")
    fun loadAllStores():List<Store>

    @Query("SELECT * FROM discounts WHERE storeId = :storeId")
    fun loadAllDiscountsByStore(storeId:Int)

    @Query("SELECT * FROM stores WHERE name LIKE :name")
    fun loadAllStoreByName(name:String)
}
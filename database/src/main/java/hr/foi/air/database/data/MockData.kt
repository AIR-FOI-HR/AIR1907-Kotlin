package hr.foi.air.database.data

import android.content.Context
import hr.foi.air.database.DAO
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

object MockData {
    private var dao:DAO? = null

    fun writeAll(context: Context){
        dao = MyDatabase.getInstance(context)?.getDao()
        val acmeStore:Store = Store()
        acmeStore?.name = "ACME store"
        acmeStore?.id = dao?.insertStores(acmeStore) as Int

        val apples:Discount = Discount()
        apples?.name = "Apples of 10%"
        apples?.discountValue = 10
        apples?.storeId = acmeStore.id

        val tuna:Discount = Discount()
        tuna?.name = "Three for two"
        tuna?.discountValue = 33
        tuna?.storeId = acmeStore.id

        dao!!.insertDiscounts(apples, tuna)



    }
}
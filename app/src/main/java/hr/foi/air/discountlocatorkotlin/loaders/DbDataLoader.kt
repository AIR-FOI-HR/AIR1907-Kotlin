package hr.foi.air.discountlocatorkotlin.loaders

import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.DAO
import hr.foi.air.database.data.MockData
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.MainActivity

class DbDataLoader : DataLoader {

    var dataLoaded: Boolean = false

    override fun loadData(listener: DataLoadedListener) {
        val dao: DAO? = MainActivity.database?.getDao()
        var stores: List<Store> = dao?.loadAllStores() ?: emptyList()
        var discounts: List<Discount> = dao?.loadAllDiscounts() ?: emptyList()

        /*
        if(stores.size == 0){
            MockData.writeAll(null)
            stores = dao?.loadAllStores() ?: emptyList()
            discounts = dao?.loadAllDiscounts() ?: emptyList()

        }
        */
        dataLoaded = true
        listener.onDataLoaded(stores, discounts)
    }

    override fun isDataLoaded(): Boolean {
        return dataLoaded
    }
}
package hr.foi.air.discountlocatorkotlin.managers

import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.core.DataPresenter
import hr.foi.air.database.DAO
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.MainActivity
import hr.foi.air.discountlocatorkotlin.loaders.DataLoaderFactory

public class DataLoaderManager {
    companion object{
        private var INSTANCE:DataLoaderManager = DataLoaderManager()

        fun getInstance(): DataLoaderManager {
            return INSTANCE
        }
    }

    fun sendData(module: DataPresenter){
        val dataLoader: DataLoader = DataLoaderFactory.getDataLoader()
        dataLoader.loadData(object : DataLoadedListener{
            override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                module.setData(stores, discounts)
            }

        })
    }

    fun syncData(){
        val dataLoader = DataLoaderFactory.getDataLoader()
        dataLoader.loadData(object : DataLoadedListener{
            override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
                val dao: DAO? = MainActivity.database?.getDao()
                dao?.deleteStores()
                dao?.deleteDiscounts()

                if (stores != null && discounts != null) {
                    for (s in stores)
                        dao?.insertStores(s)

                    for (d in discounts)
                        dao?.insertDiscounts(d)
                }
            }

        })
    }
}
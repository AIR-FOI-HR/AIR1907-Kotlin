package hr.foi.air.discountlocatorkotlin.loaders

import hr.foi.air.core.DataLoader
import hr.foi.air.database.DAO
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.MainActivity

class DataLoaderFactory {
    companion object{
        fun getDataLoader():DataLoader{
            var dataLoader: DataLoader? = null

            val stores: List<Store>? = MainActivity.database?.getDao()?.loadAllStores()

            if(stores != null && stores?.isNotEmpty()){
                dataLoader = DbDataLoader()
            }else{
                dataLoader = WsDataLoader()
            }
            return dataLoader
        }
    }
}
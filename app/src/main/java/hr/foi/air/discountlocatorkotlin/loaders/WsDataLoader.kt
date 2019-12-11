package hr.foi.air.discountlocatorkotlin.loaders

import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.webservice.MyWebserviceCaller
import hr.foi.air.webservice.handlers.MyWebserviceHandler

class WsDataLoader : DataLoader {

    private var listener: DataLoadedListener? = null
    private var stores: List<Store>? = null
    private var discounts: List<Discount>? = null

    private var storesArrived: Boolean = false
    private var discountsArrived: Boolean = false


    override fun loadData(listener: DataLoadedListener) {
        this.listener = listener

        val storeCaller: MyWebserviceCaller = MyWebserviceCaller(storeHandler)
        val discountCaller: MyWebserviceCaller = MyWebserviceCaller(discountHandler)

        storeCaller.getAll("getAll", Store::class.java)
        discountCaller.getAll("getAll", Discount::class.java)
    }

    override fun isDataLoaded(): Boolean {
        return false
    }

    @SuppressWarnings("unchecked")
    private val storeHandler: MyWebserviceHandler = object : MyWebserviceHandler{
        override fun <T> onDataArrived(result: List<T>, ok: Boolean, timeStamp: Long?) {
            if(ok){
                stores = result as List<Store>
            }
            storesArrived = true
            checkDataArrival()
        }

    }

    @SuppressWarnings("unchecked")
    private val discountHandler: MyWebserviceHandler = object : MyWebserviceHandler{
        override fun <T> onDataArrived(result: List<T>, ok: Boolean, timeStamp: Long?) {
            if(ok){
                discounts = result as List<Discount>
            }
            discountsArrived = true
            checkDataArrival()
        }

    }

    private fun checkDataArrival(){
        if(storesArrived && discountsArrived){
            listener?.onDataLoaded(stores, discounts)
        }
    }

}
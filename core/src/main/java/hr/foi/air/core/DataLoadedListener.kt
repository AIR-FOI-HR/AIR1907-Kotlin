package hr.foi.air.core

import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

interface DataLoadedListener {
    fun onDataLoaded(stores:List<Store>, discounts: List<Discount>)
}
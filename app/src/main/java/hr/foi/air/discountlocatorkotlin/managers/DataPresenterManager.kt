package hr.foi.air.discountlocatorkotlin.managers

import android.provider.ContactsContract
import com.example.map.MapModule
import hr.foi.air.core.DataPresenter
import hr.foi.air.discountlocatorkotlin.modules.ListViewModule

class DataPresenterManager {

    companion object{
        private var INSTANCE:DataPresenterManager = DataPresenterManager()

        fun getInstance(): DataPresenterManager {
            return INSTANCE
        }
    }

    var modules: MutableList<DataPresenter>? = null

    constructor(){
        modules = ArrayList<DataPresenter>()
        (modules as ArrayList<DataPresenter>).add(ListViewModule())
        (modules as ArrayList<DataPresenter>).add(MapModule())
    }
}
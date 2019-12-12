package hr.foi.air.discountlocatorkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.loaders.WsDataLoader


class MainActivity : AppCompatActivity(), DataLoadedListener {

    companion object{
        @JvmField
        public var database:MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(this)
    }

    fun loadData() {
        var dataLoader: DataLoader = WsDataLoader()
        dataLoader.loadData(this)
    }

    @SuppressWarnings("unchecked")
    override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {

    }


}

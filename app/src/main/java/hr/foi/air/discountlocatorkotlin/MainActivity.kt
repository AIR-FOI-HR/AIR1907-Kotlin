package hr.foi.air.discountlocatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.data.MockData
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.loaders.DbDataLoader
import hr.foi.air.discountlocatorkotlin.loaders.WsDataLoader

class MainActivity : AppCompatActivity(), DataLoadedListener {

    companion object{
        @JvmField
        public var database:MyDatabase? = null
    }

    var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(this)
        mockData()
        val button = findViewById<Button>(R.id.test_button)

        mListView = findViewById<ListView>(R.id.discount_list)

        button.setOnClickListener{
            val dataLoader: DataLoader = WsDataLoader()
            dataLoader.loadData(this)
        }

    }

    private fun mockData() {
        val stores: List<Store>? = database?.getDao()?.loadAllStores()
            if(stores != null && stores.isNotEmpty()){
                for (store in stores){
                    Log.d("AirAir", store.name ?:"")
                    val discounts: List<Discount>? = database?.getDao()?.loadAllDiscountsByStore(store.id)
                    if (discounts != null) {
                        Log.d("AirAir", discounts.size.toString())
                        for (discount in discounts){
                            Log.d("AirAir", discount.name ?:"")
                        }
                    }
                }
            }else{
                MockData.writeAll(this);
            }

    }
    @SuppressWarnings("unchecked")
    override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
        val listItems: MutableList<String> = ArrayList<String>()

        if (discounts != null) {
            for (discount in discounts){
                listItems.add(discount.name)
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems.toTypedArray())
        mListView?.adapter = adapter
    }


}

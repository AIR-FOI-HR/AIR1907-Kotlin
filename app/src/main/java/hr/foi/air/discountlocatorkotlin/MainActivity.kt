package hr.foi.air.discountlocatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.data.MockData
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

class MainActivity : AppCompatActivity() {

    var database:MyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(this)
        mockData();
    }

    private fun mockData() {
        val stores: List<Store>? = database?.getDao()?.loadAllStores()
        if (stores != null) {
            if(stores.isNotEmpty()){
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
    }


}

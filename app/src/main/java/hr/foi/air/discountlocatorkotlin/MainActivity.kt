package hr.foi.air.discountlocatorkotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.DAO
import hr.foi.air.database.MyDatabase
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.loaders.DataLoaderFactory
import hr.foi.air.discountlocatorkotlin.recyclerview.ExpandableStoreItem
import hr.foi.air.discountlocatorkotlin.recyclerview.StoreRecyclerAdapter


class MainActivity : AppCompatActivity(), DataLoadedListener {

    var recyclerView: RecyclerView?= null




    companion object{
        @JvmField
        public var database:MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(this)

        recyclerView = findViewById(R.id.main_recycler)
        loadData()
    }

    fun loadData() {
        var dataLoader: DataLoader = DataLoaderFactory.getDataLoader()
        dataLoader.loadData(this)
    }

    @SuppressWarnings("unchecked")
    override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
        val storeItems: MutableList<ExpandableStoreItem> = ArrayList()
        for (s in stores!!) storeItems.add(
            ExpandableStoreItem(s, discounts!!)
        )

        recyclerView?.adapter = StoreRecyclerAdapter(this, storeItems)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val dao: DAO? = database?.getDao()
        dao?.deleteStores()
        dao?.deleteDiscounts()

        for (store in stores){
            dao?.insertStores(store)
        }

        if (discounts != null) {
            for (discount in discounts){
                dao?.insertDiscounts(discount)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.preferences){
            val intent: Intent = Intent(this, SettingsActivity::class.java)
            this.startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}

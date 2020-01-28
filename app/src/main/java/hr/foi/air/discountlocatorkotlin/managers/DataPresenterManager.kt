package hr.foi.air.discountlocatorkotlin.managers

import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.map.MapModule
import com.google.android.material.navigation.NavigationView
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
    var activity: AppCompatActivity? = null
    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var dynamicGroupId: Int? = null

    constructor(){
        modules = ArrayList<DataPresenter>()
        (modules as ArrayList<DataPresenter>).add(ListViewModule())
        (modules as ArrayList<DataPresenter>).add(MapModule())
    }

    fun setDrawerDependencies(activity: AppCompatActivity, navigationView: NavigationView, drawerLayout: DrawerLayout, dynamicGroupId: Int){
        this.activity = activity
        this.navigationView = navigationView
        this.drawerLayout = drawerLayout
        this.dynamicGroupId = dynamicGroupId

        setupDrawerMenu()
    }

    private fun setupDrawerMenu() {
        for (i in 0 until modules!!.size){
            var module: DataPresenter = modules!!.get(i)
            navigationView?.menu?.add(this!!.dynamicGroupId!!, i, i+1, module.getName(this!!.activity!!))
                ?.setIcon(module.getIcon(
                    this!!.activity!!
                ))?.setCheckable(true)
        }
    }
}
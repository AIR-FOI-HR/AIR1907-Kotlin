package hr.foi.air.discountlocatorkotlin.managers

import android.provider.ContactsContract
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.map.MapModule
import com.google.android.material.navigation.NavigationView
import hr.foi.air.core.DataPresenter
import hr.foi.air.discountlocatorkotlin.R
import hr.foi.air.discountlocatorkotlin.modules.ListViewModule
import kotlinx.android.synthetic.main.content_main.view.*

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

    fun startMainModule(){
        var mainModule: DataPresenter? = modules?.get(0) ?: null
        if(mainModule != null){
            startModule(mainModule)
        }
    }

    private fun startModule(module: DataPresenter) {
        var fragmentManager: FragmentManager? = activity?.supportFragmentManager
        fragmentManager?.beginTransaction()?.replace(R.id.main_fragment, module.getFragment())
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)?.commit()
        DataLoaderManager.getInstance().sendData(module)
    }

    fun selectNavigationItem(menuItem: MenuItem){
        if(!menuItem.isChecked){
            menuItem.setChecked(true)
            drawerLayout?.closeDrawer(GravityCompat.START)

            var module: DataPresenter? = modules?.get(menuItem.itemId)
            if (module != null) {
                startModule(module)
            }
        }
    }
}
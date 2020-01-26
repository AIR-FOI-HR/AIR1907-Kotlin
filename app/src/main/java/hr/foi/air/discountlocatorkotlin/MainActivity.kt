package hr.foi.air.discountlocatorkotlin

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import hr.foi.air.core.CurrentActivity
import hr.foi.air.database.MyDatabase
import hr.foi.air.discountlocatorkotlin.fragments.DiscountDetailsFragment
import hr.foi.air.discountlocatorkotlin.helper.Util
import hr.foi.air.discountlocatorkotlin.modules.ListViewModule


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener, ListViewModule.OnFragmentInteractionListener, DiscountDetailsFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    var recyclerView: RecyclerView?= null
    private val util: Util =  Util()
    var toolbar: androidx.appcompat.widget.Toolbar? = null
    var drawerLayout: DrawerLayout? = null
    var drawerToggle: ActionBarDrawerToggle? = null
    var navigationView: NavigationView? = null

    companion object{
        @JvmField
        public var database:MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CurrentActivity.setActivity(this)
        util.setLanguage(this)
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
        database = MyDatabase.getInstance(this)
        initializeLayout()
        showMainFragment()
    }

    private fun initializeLayout() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView!!.setNavigationItemSelectedListener(this)


    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment,
            ListViewModule()
        ).commit()
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

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == "language"){
            util.setLanguage(this)
            this.recreate()
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }


}

package hr.foi.air.discountlocatorkotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.database.MyDatabase
import hr.foi.air.discountlocatorkotlin.helper.Util


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    var recyclerView: RecyclerView?= null
    private val util: Util =  Util()



    companion object{
        @JvmField
        public var database:MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        util.setLanguage(this)
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
        database = MyDatabase.getInstance(this)
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


}

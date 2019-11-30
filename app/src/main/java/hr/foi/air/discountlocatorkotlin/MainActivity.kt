package hr.foi.air.discountlocatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.foi.air.database.MyDatabase

class MainActivity : AppCompatActivity() {

    var database:MyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MyDatabase.getInstance(this)
    }
}

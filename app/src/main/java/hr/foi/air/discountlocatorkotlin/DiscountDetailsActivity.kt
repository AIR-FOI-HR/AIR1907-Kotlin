package hr.foi.air.discountlocatorkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import hr.foi.air.database.entities.Discount
import org.w3c.dom.Text
import java.text.SimpleDateFormat

class DiscountDetailsActivity : AppCompatActivity() {

    var discountName: TextView? = null
    var discountDescription: TextView? = null
    var discountStartDate: TextView? = null
    var discountEndDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount_details)

        discountName = findViewById(R.id.discount_details_name)
        discountDescription = findViewById(R.id.discount_details_description)
        discountStartDate = findViewById(R.id.discount_details_start)
        discountEndDate = findViewById(R.id.discount_details_end)
    }

    override fun onResume() {
        super.onResume()

        val intent: Intent = intent

        val discountId: Int = intent.getIntExtra("id", -1)

        if(discountId != -1){
            val discount: Discount? = MainActivity.database?.getDao()?.getDiscountsById(discountId)

            discountName?.text  = discount?.name
            discountDescription?.text = discount?.description

            val sdf:SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
            discountStartDate?.text = sdf.format(discount?.startDate)
            discountEndDate?.text = sdf.format(discount?.endDate)

        }

    }
}

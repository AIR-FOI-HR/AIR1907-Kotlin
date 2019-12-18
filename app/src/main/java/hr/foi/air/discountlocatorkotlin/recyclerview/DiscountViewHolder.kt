package hr.foi.air.discountlocatorkotlin.recyclerview

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.DiscountDetailsActivity
import hr.foi.air.discountlocatorkotlin.R

class DiscountViewHolder: ChildViewHolder<Discount> {

    var discountName: TextView? = null
    var discountDesc: TextView? = null
    var discountValue: TextView? = null

    private var selectedDiscount: Discount? = null

    constructor(@NonNull itemView: View): super(itemView){
        discountName = itemView.findViewById(R.id.discount_name)
        discountDesc = itemView.findViewById(R.id.discount_desc)
        discountValue = itemView.findViewById(R.id.discount_value)
        itemView.setOnClickListener{
            Toast.makeText(it.context, selectedDiscount?.name, Toast.LENGTH_SHORT).show()

            val intent: Intent = Intent(it.context, DiscountDetailsActivity::class.java)
            intent.putExtra("id", selectedDiscount?.id)
            it.context.startActivity(intent)
        }
    }

    public fun BindToData(discount: Discount){
        this.selectedDiscount = discount
        discountName?.text = discount.name
        discountDesc?.text = discount.description
        discountValue?.text = (discount.discountValue).toString()
    }

}
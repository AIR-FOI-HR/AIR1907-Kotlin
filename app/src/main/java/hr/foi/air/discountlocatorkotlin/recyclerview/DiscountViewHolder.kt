package hr.foi.air.discountlocatorkotlin.recyclerview

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import hr.foi.air.database.entities.Discount
import hr.foi.air.discountlocatorkotlin.MainActivity
import hr.foi.air.discountlocatorkotlin.R

class DiscountViewHolder: ChildViewHolder<Discount> {

    var discountName: TextView? = null
    var discountDesc: TextView? = null
    var discountValue: TextView? = null

    private var selectedDiscount: Discount? = null
    private var adapter: StoreRecyclerAdapter? = null

    constructor(@NonNull itemView: View, adapter: StoreRecyclerAdapter): super(itemView){
        this.adapter = adapter
        discountName = itemView.findViewById(R.id.discount_name)
        discountDesc = itemView.findViewById(R.id.discount_desc)
        discountValue = itemView.findViewById(R.id.discount_value)
        itemView.setOnClickListener{
            Toast.makeText(it.context, selectedDiscount?.name, Toast.LENGTH_SHORT).show()
            
        }

        itemView.setOnLongClickListener {
            val parentPosition: Int = parentAdapterPosition
            val childPosition: Int = childAdapterPosition
            val povrat: Boolean = true

            val alertDialog: AlertDialog = AlertDialog.Builder(itemView.context).create()

            alertDialog.setTitle("Do you want to remove selected item?")

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"
            ) { dialog, which ->  alertDialog.dismiss()}

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { dialog, which ->
                MainActivity.database?.getDao()?.deleteDiscounts(selectedDiscount)

                adapter.parentList[parentPosition].childList?.drop(parentPosition)
                adapter.notifyChildRemoved(parentPosition, childPosition)
                adapter.notifyDataSetChanged()

                if(adapter.parentList[parentPosition].childList.isNullOrEmpty()){
                    MainActivity.database?.getDao()?.deleteStoreById(selectedDiscount?.id)
                    adapter.parentList.removeAt(parentPosition)
                    adapter.notifyParentRemoved(parentPosition)
                    adapter.notifyDataSetChanged()
                }

                alertDialog.dismiss()
            }

            alertDialog.show()

            true
        }
    }

    public fun BindToData(discount: Discount){
        this.selectedDiscount = discount
        discountName?.text = discount.name
        discountDesc?.text = discount.description
        discountValue?.text = (discount.discountValue).toString()
    }

}
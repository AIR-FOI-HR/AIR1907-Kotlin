package hr.foi.air.discountlocatorkotlin.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.squareup.picasso.Picasso
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.R
import kotlinx.android.synthetic.main.store_list_item.view.*

class StoreViewHolder : ParentViewHolder<ExpandableStoreItem, Discount> {

    var storeName: TextView? = null
    var storeDesc: TextView? = null
    var storeImage: ImageView? = null

    constructor(@NonNull itemView: View): super(itemView){
        storeName = itemView.findViewById(R.id.store_name)
        storeDesc = itemView.findViewById(R.id.store_desc)
        storeImage = itemView.findViewById(R.id.store_image)
    }

    public fun BindToData(store:Store){
        storeName?.text = store.name
        storeDesc?.text = store.description
        Picasso.with(itemView.context).load(store.imgUrl).into(storeImage)
    }
}
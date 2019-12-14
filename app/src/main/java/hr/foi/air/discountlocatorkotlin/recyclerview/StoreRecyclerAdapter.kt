package hr.foi.air.discountlocatorkotlin.recyclerview

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import hr.foi.air.database.entities.Discount



class StoreRecyclerAdapter: ExpandableRecyclerAdapter<ExpandableStoreItem, Discount, StoreViewHolder, DiscountViewHolder>{

    private var context: Context? = null

    constructor(context: Context, @NonNull parentList: List<ExpandableStoreItem> ) :super(parentList) {
        this.context = context
    }

    @NonNull
    override fun onCreateParentViewHolder (parentViewGroup: ViewGroup, viewType: Int): StoreViewHolder {
        val storeView: View = LayoutInflater.from(context).inflate(
            hr.foi.air.discountlocatorkotlin.R.layout.store_list_item, parentViewGroup, false
        )
        return StoreViewHolder(storeView)
    }

    @NonNull
    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): DiscountViewHolder {
        val discountView: View = LayoutInflater.from(context).inflate(hr.foi.air.discountlocatorkotlin.R.layout.discount_list_item, childViewGroup, false)
        return DiscountViewHolder(discountView)
    }

    override fun onBindParentViewHolder(parentViewHolder: StoreViewHolder, parentPosition: Int, parent: ExpandableStoreItem) {
        parentViewHolder.BindToData(parent);
    }

    override fun onBindChildViewHolder(childViewHolder: DiscountViewHolder, parentPosition: Int, childPosition: Int, child: Discount) {
        childViewHolder.BindToData(child)
    }
}
package hr.foi.air.discountlocatorkotlin.recyclerview

import com.bignerdranch.expandablerecyclerview.model.Parent
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

class ExpandableStoreItem : Store, Parent<Discount>  {
    private var discounts: MutableList<Discount>? = null

    constructor(store:Store, allDiscounts: List<Discount>): super(store){
        discounts =ArrayList()
        for (discount in allDiscounts){
            if (discount.storeId == this.id){
                (discounts as ArrayList<Discount>).add(discount)
            }
        }
    }

    override fun getChildList(): List<Discount>? {
        return discounts
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }
}
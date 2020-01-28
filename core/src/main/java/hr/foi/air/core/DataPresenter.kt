package hr.foi.air.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store

interface DataPresenter {
    fun getIcon(context: Context): Drawable
    fun getName(context: Context): String
    fun getFragment(): Fragment
    fun setData(stores: List<Store>?, discounts: List<Discount>?)
}
package com.example.map

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.foi.air.core.DataPresenter
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.map.R

class MapModule : Fragment(), OnMapReadyCallback, DataPresenter {

    var map: GoogleMap? = null
    var mapFragment: SupportMapFragment? = null

    var stores: List<Store>? = null
    var discounts: List<Discount>? = null

    private var moduleReadyFlag: Boolean = false
    private var dataReadyFlag: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        moduleReadyFlag = true
        tryToDisplayData()
    }

    private fun tryToDisplayData() {
        if(moduleReadyFlag && dataReadyFlag){
            displayData()
        }
    }

    private fun displayData() {
        var cameraReady: Boolean = false
        if(stores != null){
            for (s in stores!!){
                val position: LatLng = LatLng(s.latitude!! / 1000000.0, s.longitude!! / 1000000.0)
                map?.addMarker(MarkerOptions().position(position).title(s.name))

                if(!cameraReady){
                    map?.moveCamera(CameraUpdateFactory.newLatLng(position))
                    map?.moveCamera(CameraUpdateFactory.zoomTo(12F))
                    cameraReady = true
                }
            }
        }
    }

    override fun getIcon(context: Context): Drawable {
        return context.getDrawable(android.R.drawable.ic_menu_mylocation)!!
    }

    override fun getName(context: Context): String {
        return context.getString(R.string.map_view)
    }

    override fun getFragment(): Fragment {
        return this
    }

    override fun setData(
        stores: List<Store>?,
        discounts: List<Discount>?
    ) {
        this.stores = stores
        this.discounts = discounts

        dataReadyFlag = true
    }

}
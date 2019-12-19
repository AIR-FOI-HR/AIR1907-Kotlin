package hr.foi.air.discountlocatorkotlin.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.air.core.DataLoadedListener
import hr.foi.air.core.DataLoader
import hr.foi.air.database.DAO
import hr.foi.air.database.entities.Discount
import hr.foi.air.database.entities.Store
import hr.foi.air.discountlocatorkotlin.MainActivity.Companion.database
import hr.foi.air.discountlocatorkotlin.R
import hr.foi.air.discountlocatorkotlin.loaders.DataLoaderFactory
import hr.foi.air.discountlocatorkotlin.recyclerview.ExpandableStoreItem
import hr.foi.air.discountlocatorkotlin.recyclerview.StoreRecyclerAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListViewFragment : Fragment(), DataLoadedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view?.findViewById(R.id.main_recycler)
        loadData()
    }

    private fun loadData() {
        val dataLoader: DataLoader = DataLoaderFactory.getDataLoader()
        dataLoader.loadData(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDataLoaded(stores: List<Store>?, discounts: List<Discount>?) {
        val storeItems: MutableList<ExpandableStoreItem> = ArrayList()
        if (stores != null && discounts != null) {
            for (s in stores)
                storeItems.add(ExpandableStoreItem(s, discounts))
        }
        if(context != null){
            recyclerView?.setAdapter(StoreRecyclerAdapter(context!!, storeItems))
            recyclerView?.setLayoutManager(LinearLayoutManager(context))
        }

        //data storage
        val dao: DAO? = database?.getDao()
        dao?.deleteStores()
        dao?.deleteDiscounts()

        if (stores != null) {
            for (s in stores) dao?.insertStores(s)
        }
        if (discounts != null) {
            for (d in discounts) dao?.insertDiscounts(d)
        }
    }
}

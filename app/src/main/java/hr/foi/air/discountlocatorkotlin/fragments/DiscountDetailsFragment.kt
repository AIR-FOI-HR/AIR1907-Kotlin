package hr.foi.air.discountlocatorkotlin.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hr.foi.air.database.entities.Discount
import hr.foi.air.discountlocatorkotlin.MainActivity

import hr.foi.air.discountlocatorkotlin.R
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DiscountDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DiscountDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscountDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var discountName: TextView? = null
    private var discountDescription: TextView? = null
    private var discountStartDate: TextView? = null
    private var discountEndDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        discountName = view.findViewById(R.id.discount_details_name)
        discountDescription = view.findViewById(R.id.discount_details_description)
        discountStartDate = view.findViewById(R.id.discount_details_start)
        discountEndDate = view.findViewById(R.id.discount_details_end)

        showDiscountDetails()
    }

    private fun showDiscountDetails() {
        val discountId: Int? = arguments?.getInt("id", -1)

        if(discountId != null && discountId != -1){
            val discount: Discount? = MainActivity.database?.getDao()?.getDiscountsById(discountId)
            discountName?.text = discount?.name
            discountDescription?.text = discount?.description

            val sdf:SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

            discountStartDate?.text = sdf.format(discount?.startDate)
            discountEndDate?.text = sdf.format(discount?.endDate)
        }

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
         * @return A new instance of fragment DiscountDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscountDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

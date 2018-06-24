package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.frigy.frigymobile.Adapters.CheckinBasketItemAdapter

import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckinBasketViewModel

private const val PAGE_TITLE: String = "pageTitle"
private const val PAGE_NUMBER: String = "pageNumber"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CheckInConfirmFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CheckInConfirmFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CheckInConfirmFragment : Fragment() {
    private var pageTitle: String = "title"
    private var pageNumber: Int = 0

    private lateinit var itemRecycler: RecyclerView
    private lateinit var viewAdapter: CheckinBasketItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: CheckinBasketViewModel

    //private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageTitle = it.getString(PAGE_TITLE)
            pageNumber = it.getInt(PAGE_NUMBER)
        }

        viewModel = ViewModelProviders.of(activity).get(CheckinBasketViewModel::class.java)
        viewModel.mBasketItems.observe(this, Observer { it -> viewAdapter.updateItems(it!!) })
        viewManager = LinearLayoutManager(activity)
        viewAdapter = CheckinBasketItemAdapter(activity, viewModel)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkin_confirm, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRecycler = view!!.findViewById(R.id.confirm_recycler)
        itemRecycler.layoutManager = viewManager
        itemRecycler.adapter = viewAdapter
    }

    // TODO: Rename method, update argument and hook method into UI event
/*    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

/*    override fun onAttach(context: Context) {
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
    }*/

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
/*    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CheckInConfirmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(pageTitle: String, pageNumber: Int) =
                CheckInConfirmFragment().apply {
                    arguments = Bundle().apply {
                        putString(PAGE_TITLE, pageTitle)
                        putInt(PAGE_NUMBER, pageNumber)
                    }
                }
    }
}

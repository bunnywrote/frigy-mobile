package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase

import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.ItemListViewModel

class ProductDetailsFragment : Fragment() {
    private val ITEM_ID = "product_item_id"

    private lateinit var productItem: Item
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mDb : FridgyInternalDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productItemId = arguments.getLong(ITEM_ID) as Long

        mDb = FridgyInternalDatabase.getInstance(activity)

        var viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)
        viewModel!!.getItem(productItemId).observe(this, Observer { item ->
            this.productItem = item!!
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val ITEM_ID = "product_item_id"

        @JvmStatic
        fun newInstance(itemId: Long):ProductDetailsFragment{
            val args = Bundle()
            args.putLong(ITEM_ID, itemId)
            val fragment = ProductDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

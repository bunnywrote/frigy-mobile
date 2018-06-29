package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.frigy.frigymobile.Extensions.DateTimeExtensions
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.ItemListViewModel

class ProductDetailsFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var productItem: Item
    private lateinit var viewModel: ItemListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity).get(ItemListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var productName = view?.findViewById(R.id.productName) as TextView
        var createdDate = view?.findViewById(R.id.createDate) as TextView

        viewModel.currentItem.observe(this, Observer{item ->
            productItem = item!!

            productName.text = productItem.product.title
            createdDate.text = "eingestellt am " + DateTimeExtensions.toSimpleString(productItem.created)
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProductDetailsFragment().apply {

        }
    }
}

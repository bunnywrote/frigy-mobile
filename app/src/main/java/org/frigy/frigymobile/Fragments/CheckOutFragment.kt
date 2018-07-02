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
import android.widget.AdapterView
import android.widget.ListView
import org.frigy.frigymobile.Adapters.CheckOutBasketItemAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckOutBasketViewModel


class CheckOutFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var viewModel: CheckOutBasketViewModel? = null
    var items: List<Item> = listOf()

    private lateinit var checkOutAdapter: CheckOutBasketItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkOutAdapter = CheckOutBasketItemAdapter(activity, arrayListOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_out, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var itemsList = view?.findViewById(R.id.item_list) as ListView

        itemsList!!.adapter = checkOutAdapter
        itemsList!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->

            val selectedItem = items.get(position)

            if(selectedItem?.isSelected){
                viewModel?.removeItem(selectedItem)
            }else{
                viewModel?.addItem(selectedItem)
            }

            checkOutAdapter.setSelected(position)
//            Toast.makeText(context, items.get(position)?.product?.title,Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProviders.of(activity).get(CheckOutBasketViewModel::class.java)
        viewModel!!.getAll().observe(this, Observer { items ->
            this.items = items!!
            checkOutAdapter.addItems(this.items)
        })
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

        @JvmStatic
        fun newInstance() =
                CheckOutFragment().apply {}
    }
}

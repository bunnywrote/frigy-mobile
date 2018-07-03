package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import org.frigy.frigymobile.Adapters.CheckOutConfirmAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckOutBasketViewModel


class CheckOutConfirmFragment :  Fragment() {

    private lateinit var viewModel: CheckOutBasketViewModel
    var items: List<Item> = listOf()
    private lateinit var checkOutConfirmAdapter: CheckOutConfirmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkOutConfirmAdapter = CheckOutConfirmAdapter(activity, arrayListOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_out_confirm, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView = view?.findViewById(R.id.basketItems) as ListView

        recyclerView!!.adapter = checkOutConfirmAdapter

//        recyclerView!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
//
//            val selectedItem = items.get(position)
//
//            if(selectedItem?.isSelected){
//                viewModel?.removeItem(selectedItem)
//            }else{
//                viewModel?.addItem(selectedItem)
//            }
//
//            checkOutAdapter.notifyDataSetChanged()
//   //         checkOutAdapter.setSelected(position)
////            Toast.makeText(context, items.get(position)?.product?.title,Toast.LENGTH_SHORT).show()
//        }

        viewModel = ViewModelProviders.of(activity).get(CheckOutBasketViewModel::class.java)
        viewModel!!.mBasketItems.observe(this, Observer { items ->
            this.items = items!!
            checkOutConfirmAdapter.addItems(this.items.toMutableList())
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                CheckOutConfirmFragment().apply {}
    }
}
package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import org.frigy.frigymobile.Adapters.SearchItemAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.ItemListViewModel


class SearchProductFragment : Fragment() {

    var itemsList: List<Item> = listOf()

    private lateinit var searchItemsAdapter: SearchItemAdapter
    private lateinit var mDb : FridgyInternalDatabase
    private var viewModel: ItemListViewModel? = null
    private lateinit var listener: OnFragmentInteractionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDb = FridgyInternalDatabase.getInstance(activity)
        searchItemsAdapter = SearchItemAdapter(activity, arrayListOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_search_product, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var search_results = view?.findViewById(R.id.search_results) as ListView
        var search_text = view?.findViewById(R.id.search_text) as TextView

        search_results!!.adapter = searchItemsAdapter
        search_results!!.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->

            var selectedItem = itemsList.get(position)

            listener.onItemSelected(selectedItem)

            viewModel!!.setCurrentItem(selectedItem)
        }

        viewModel = ViewModelProviders.of(activity).get(ItemListViewModel::class.java)
        viewModel!!.getListItems().observe(this, Observer { items ->
            this.itemsList = items!!
            searchItemsAdapter.addItems(this.itemsList)
        })

        search_text!!.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                activity.runOnUiThread(java.lang.Runnable {
                    searchItemsAdapter.replaceItems(searchItem(search_text.text.toString()))
                })
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        //listener = null
    }

    private fun searchItem(keyword: String): ArrayList<Item>{
        return ArrayList(itemsList.filter { item -> item.product.title.startsWith(keyword, true) })
    }

    interface OnFragmentInteractionListener {
        fun onItemSelected(item: Item)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                SearchProductFragment().apply {
                }
    }
}

package org.frigy.frigymobile.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
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
import android.widget.Toast
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
    private var listener: OnFragmentInteractionListener? = null

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
            Toast.makeText(activity, "Show details of " + itemsList.get(position)?.product?.title, Toast.LENGTH_SHORT).show()

//            var intent = ProductDetailsActivity.newIntent(this, itemsList.get(position))
//            startActivity(intent)
        }

        viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)
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

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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

    private fun searchItem(keyword: String): ArrayList<Item>{
        return ArrayList(itemsList.filter { item -> item.product.title.startsWith(keyword, true) })
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
         * @return A new instance of fragment SearchProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                SearchProductFragment().apply {
                }
    }
}

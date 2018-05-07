package org.frigy.frigymobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import org.frigy.frigymobile.Adapters.SearchItemAdapter
import org.frigy.frigymobile.Models.Item

class SearchActivity : AppCompatActivity() {

    private var itemsList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initList()

        var searchItemsAdapter = SearchItemAdapter(this, itemsList)

        search_results.adapter = searchItemsAdapter
        search_results.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + itemsList[position].product.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initList(){
        itemsList.add(Item("Apple"))
        itemsList.add(Item("Beer"))
        itemsList.add(Item("Milk"))
    }
}

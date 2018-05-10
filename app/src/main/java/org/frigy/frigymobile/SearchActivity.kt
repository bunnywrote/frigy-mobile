package org.frigy.frigymobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import org.frigy.frigymobile.Adapters.SearchItemAdapter
import org.frigy.frigymobile.Models.Item

class SearchActivity : AppCompatActivity() {

    private var itemsList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initList()

        var searchItemsAdapter = SearchItemAdapter(this, itemsList)
        var search_results = this?.findViewById<ListView>(R.id.search_results) as ListView

        search_results.adapter = searchItemsAdapter
        search_results.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + itemsList[position].product.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initList(){
        itemsList.add(Item("Apple"))
        itemsList.add(Item("Banana"))
        itemsList.add(Item("Beer"))
        itemsList.add(Item("Karotten"))
        itemsList.add(Item("Milk"))
        itemsList.add(Item("Joghurt"))
    }
}

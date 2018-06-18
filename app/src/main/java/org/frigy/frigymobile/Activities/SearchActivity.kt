package org.frigy.frigymobile.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import org.frigy.frigymobile.Adapters.SearchItemAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.ItemListViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var mDb : FridgyInternalDatabase
    private var viewModel: ItemListViewModel? = null

    var itemsList: List<Item> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mDb = FridgyInternalDatabase.getInstance(this)

        var searchItemsAdapter = SearchItemAdapter(this, arrayListOf())

        var search_results = this?.findViewById<ListView>(R.id.search_results) as ListView
        var search_text = this?.findViewById<EditText>(R.id.search_text) as TextView

        search_results.adapter = searchItemsAdapter
        search_results.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Show details of " + itemsList.get(position)?.product?.title, Toast.LENGTH_SHORT).show()

            var intent = ProductDetailsActivity.newIntent(this, itemsList.get(position))
            startActivity(intent)
        }

        viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)
        viewModel!!.getListItems().observe(this, Observer { items ->
            this.itemsList = items!!
            searchItemsAdapter.addItems(this.itemsList)
        })

        search_text.addTextChangedListener(object: TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                runOnUiThread(java.lang.Runnable {
                    searchItemsAdapter.replaceItems(searchItem(search_text.text.toString()))
                })
            }
        })
    }

    private fun searchItem(keyword: String): ArrayList<Item>{
        return ArrayList(itemsList.filter { item -> item.product.title.startsWith(keyword, true) })
    }

    private fun showToaster(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initList(){
//        val apple = Product()
//        apple.title = "Apple"
//        apple.quantityUnit = QuantityUnit.PIECE
//        apple.quantity = 1.0
//
//        val ananas = Product()
//        ananas.title = "Ananas"
//        ananas.quantityUnit = QuantityUnit.PIECE
//        ananas.quantity = 1.0
//
//        val bier = Product()
//        bier.title = "Bier"
//        bier.quantityUnit = QuantityUnit.PIECE
//        bier.quantity = 1.0
//
//        val cola = Product()
//        cola.title = "Cola"
//        cola.quantityUnit = QuantityUnit.PIECE
//        cola.quantity = 1.0
//
//
//        mDb.itemDao().insert(Item(apple, Date(), 1))
//        mDb.itemDao().insert(Item(ananas, Date(), 2))
//        mDb.itemDao().insert(Item(bier, Date(), 3))
//        mDb.itemDao().insert(Item(cola, Date(), 4))

//        var items : LiveData<List<Item>> = mDb.itemDao().getAll()
//
//        items.observe(this, (Observer<List<Item>> {
//            if(!items?.value!!.isEmpty()){
//                runOnUiThread(java.lang.Runnable {
//                    this.itemsList = items.value!!
//                })
//            }
//        }))
        //this.itemsList
    }
}

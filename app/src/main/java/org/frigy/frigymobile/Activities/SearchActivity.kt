package org.frigy.frigymobile.Activities

import android.arch.lifecycle.LiveData
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import org.frigy.frigymobile.Adapters.SearchItemAdapter
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.R

class SearchActivity : AppCompatActivity() {

    private lateinit var mDb : FridgyInternalDatabase
    private lateinit var itemsList: LiveData<List<Item>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mDb = FridgyInternalDatabase.getInstance(this)
        initList()

        var searchItemsAdapter = SearchItemAdapter(this, itemsList)

        var search_results = this?.findViewById<ListView>(R.id.search_results) as ListView
        var search_text = this?.findViewById<EditText>(R.id.search_text) as TextView

        search_results.adapter = searchItemsAdapter
        search_results.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Show details of " + itemsList.value?.get(position)?.product?.title, Toast.LENGTH_SHORT).show()
        }

        search_text.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                //Thread(Runnable {
                  //  searchItemsAdapter.replaceItems(searchItem(search_text.text.toString()))
                //})

                runOnUiThread(java.lang.Runnable {
                    //searchItemsAdapter.replaceItems(searchItem(search_text.text.toString()))
                })
              //  showToaster(search_text.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //showToaster("before changed")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              //  showToaster("on changed")
            }
        })
    }

    private fun searchItem(keyword: String): ArrayList<Item>{
        //return ArrayList(itemsList.filter { item -> item.product.title.startsWith(keyword, true) })
        return ArrayList()
    }

    private fun showToaster(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initList(){
//        itemsList.add(Item("Apple"))
//        itemsList.add(Item("Banana"))
//        itemsList.add(Item("Beer"))
//        itemsList.add(Item("Karotten"))
//        itemsList.add(Item("Milk"))
//        itemsList.add(Item("Joghurt"))
//
//        val db  = Room.databaseBuilder <FridgyInternalDatabase>(applicationContext, FridgyInternalDatabase::class.java, "room-sample-db")
//                .fallbackToDestructiveMigration()
//                .build()

//        val apple = Product()
//        apple.title = "Apple"
//
//        val cola = Product()
//        cola.title = "Cola"
//
//
//        mDb.itemDao().insert(Item(1, apple, Date()))
//        mDb.itemDao().insert(Item(2, cola, Date()))

//        db.itemDao().insert(Item("Banana"));
//        db.itemDao().insert(Item("Beer"));
//        db.itemDao().insert(Item("Carrots"));
//        db.itemDao().insert(Item("Milk"));
//        db.itemDao().insert(Item("Yoghurt"));

        itemsList = mDb.itemDao().getAll()
    }
}

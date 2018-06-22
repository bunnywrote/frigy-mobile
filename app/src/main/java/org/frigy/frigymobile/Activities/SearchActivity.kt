package org.frigy.frigymobile.Activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import org.frigy.frigymobile.Fragments.SearchProductFragment
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Models.QuantityUnit
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.R
import java.util.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initList()

        supportFragmentManager.inTransaction {
            add(R.id.containerLayout, SearchProductFragment.newInstance())
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
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
//        var mDb = FridgyInternalDatabase.getInstance(this)
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

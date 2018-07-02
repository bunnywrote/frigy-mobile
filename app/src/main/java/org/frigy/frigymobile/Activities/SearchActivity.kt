package org.frigy.frigymobile.Activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import org.frigy.frigymobile.Fragments.ProductDetailsFragment
import org.frigy.frigymobile.Fragments.SearchProductFragment
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R

class SearchActivity : AppCompatActivity(), SearchProductFragment.OnFragmentInteractionListener {

    override fun onItemSelected(item: Item) {
     //   Toast.makeText(this, "Show details of " + item?.product?.title, Toast.LENGTH_SHORT).show()

        supportFragmentManager.inTransaction {
            replace(R.id.containerLayout, ProductDetailsFragment.newInstance())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager.inTransaction {
            add(R.id.containerLayout, SearchProductFragment.newInstance())
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}

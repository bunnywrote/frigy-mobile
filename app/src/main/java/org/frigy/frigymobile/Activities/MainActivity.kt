package org.frigy.frigymobile.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.app_bar_main.*
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Models.QuantityUnit
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase
import org.frigy.frigymobile.R
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initialize()


    /*
    *  dbInit() is necessary by first start
    */
    //   dbInit()
    }

    override fun onBackPressed() {
//        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            drawer_layout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
//        when (item.itemId) {
//            R.id.nav_camera -> {
//                // Handle the camera action
//            }
//            R.id.nav_gallery -> {
//
//            }
//            R.id.nav_slideshow -> {
//
//            }
//            R.id.nav_manage -> {
//
//            }
//            R.id.nav_share -> {
//
//            }
//            R.id.nav_send -> {
//
//            }
//        }
//
//        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun initialize(){
        val search = findViewById<RelativeLayout>(R.id.search);
        search.setOnClickListener{view ->
            search()
        }

        val checkIn = findViewById<RelativeLayout>(R.id.check_in);
        checkIn.setOnClickListener{view ->
            checkIn()
        }

        val checkOut = findViewById<RelativeLayout>(R.id.check_out);
        checkOut.setOnClickListener{view ->
            checkOut()
        }
    }

    fun dbInit(){
        val apple = Product()
        apple.title = "Apple"
        apple.quantityUnit = QuantityUnit.PIECE
        apple.quantity = 1.0

        val ananas = Product()
        ananas.title = "Ananas"
        ananas.quantityUnit = QuantityUnit.PIECE
        ananas.quantity = 1.0

        val bier = Product()
        bier.title = "Bier"
        bier.quantityUnit = QuantityUnit.PIECE
        bier.quantity = 1.0

        val cola = Product()
        cola.title = "Cola"
        cola.quantityUnit = QuantityUnit.PIECE
        cola.quantity = 1.0

        var mDb = FridgyInternalDatabase.getInstance(this)


        mDb.itemDao().insert(Item(apple, Date(), 1))
        mDb.itemDao().insert(Item(ananas, Date(), 2))
        mDb.itemDao().insert(Item(bier, Date(), 3))
        mDb.itemDao().insert(Item(cola, Date(), 4))

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

    fun search(){
        val intent = Intent(this, SearchActivity::class.java)
        startActivityForResult(intent, Context.CONTEXT_INCLUDE_CODE)
    }

    fun checkIn(){
        val intent = Intent(this, CheckInActivity::class.java)
        startActivityForResult(intent, Context.CONTEXT_INCLUDE_CODE)
    }

    fun checkOut(){
        val intent = Intent(this, CheckOutActivity::class.java)
        startActivityForResult(intent, Context.CONTEXT_INCLUDE_CODE)
    }
}

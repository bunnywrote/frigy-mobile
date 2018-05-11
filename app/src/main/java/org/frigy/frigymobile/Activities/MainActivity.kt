package org.frigy.frigymobile.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.app_bar_main.*
import org.frigy.frigymobile.R
import org.frigy.frigymobile.service.AccountCreator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val manager =  supportFragmentManager;
    lateinit var fragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        AccountCreator.CreateSyncAccount(this);

        initialize()

//        val transaction = manager.beginTransaction();
//        val fragment = MainFragment();
//        transaction.replace(R.id.fragment_holder, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit()


//        var testBtn = findViewById<TextView>(R.id.testBtn);
//        testBtn.setOnClickListener { view ->
//            searchProduct()
//        }

//        val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        nav_view.setNavigationItemSelectedListener(this)
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

//        var fragmentHolder = findViewById<FrameLayout>(R.id.fragment_holder);
//        fragmentHolder.visibility = View.VISIBLE
//
//        val transaction = manager.beginTransaction();
//        val fragment = SearchProductFragment();
//        transaction.replace(R.id.fragment_holder, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit()
}

package org.frigy.frigymobile.Activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import org.frigy.frigymobile.Adapters.CheckinBasketItemAdapter
import org.frigy.frigymobile.Fragments.CheckInConfirmFragment
import org.frigy.frigymobile.Fragments.CheckInConfirmFragment.Companion.newInstance
import org.frigy.frigymobile.Fragments.CheckInScanFragment
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Persistence.ProductRepository
import org.frigy.frigymobile.R
import org.frigy.frigymobile.ViewModels.CheckinBasketViewModel

class CheckInActivity : AppCompatActivity() {

    //private lateinit var viewModel: CheckinBasketViewModel
    private lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO to Main Activity
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }

        setContentView(R.layout.activity_check_in)

        pager = findViewById(R.id.checkin_pager)
        val pagerAdapter: CheckinPagerAdapter = CheckinPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        if (pager.currentItem === 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            pager.currentItem = (pager.currentItem - 1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.process_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.proceed -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    class CheckinPagerAdapter(private val manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        //TODO add translations
        val pageMapping: Array<String> = arrayOf("Scan", "Confirm")

        override fun getItem(position: Int): Fragment? {

            when (position) {
                0 -> return CheckInScanFragment.newInstance(pageMapping[position], position)
                1 -> return CheckInConfirmFragment.newInstance(pageMapping[position], position)
            }
            return null
        }

        override fun getCount(): Int {
            return pageMapping.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return pageMapping[position]
        }

    }

}

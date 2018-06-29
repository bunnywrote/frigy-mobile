package org.frigy.frigymobile.Activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import org.frigy.frigymobile.Fragments.CheckInConfirmFragment
import org.frigy.frigymobile.Fragments.CheckInScanFragment
import org.frigy.frigymobile.R

class CheckInActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO move all check permissions to Main Activity
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }

        setContentView(R.layout.activity_check_in)

        pager = findViewById(R.id.checkin_pager)
        val pagerAdapter: CheckinPagerAdapter = CheckinPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            super.onBackPressed()
        } else {
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
            if (pager.currentItem < (pager.adapter.count - 1)) {
                pager.currentItem = (pager.currentItem + 1)
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    class CheckinPagerAdapter(private val manager: FragmentManager) : FragmentPagerAdapter(manager) {

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

package org.frigy.frigymobile.Activities

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.app_bar_main.*
import org.frigy.frigymobile.Fragments.CheckOutConfirmFragment
import org.frigy.frigymobile.Fragments.CheckOutFragment
import org.frigy.frigymobile.R

class CheckOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_check_out)
        setSupportActionBar(toolbar)

        supportFragmentManager.inTransaction {
            add(R.id.containerLayout, CheckOutFragment.newInstance())
        }

        val confirmBtn = findViewById<TextView>(R.id.confirmBtn) as TextView

        confirmBtn.setOnClickListener{view ->
            supportFragmentManager.inTransaction {
                replace(R.id.containerLayout, CheckOutConfirmFragment.newInstance())
            }
        }

    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}

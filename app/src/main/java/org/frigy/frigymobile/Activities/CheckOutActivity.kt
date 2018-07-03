package org.frigy.frigymobile.Activities

import android.content.Context
import android.content.Intent
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

enum class CheckoutState{
    ConfirmSelected,
    ConfirmPicked
}

class CheckOutActivity : AppCompatActivity() {

    private var checkoutState: CheckoutState = CheckoutState.ConfirmSelected

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

            if(checkoutState == CheckoutState.ConfirmSelected){
                checkoutState = CheckoutState.ConfirmPicked

                supportFragmentManager.inTransaction {
                    replace(R.id.containerLayout, CheckOutConfirmFragment.newInstance())
                }
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent, Context.CONTEXT_INCLUDE_CODE)
            }
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}

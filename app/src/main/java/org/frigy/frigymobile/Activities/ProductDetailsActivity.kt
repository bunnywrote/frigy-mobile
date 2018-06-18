package org.frigy.frigymobile.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.R

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
    }

    companion object {

        //private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context, item: Item?): Intent {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            //intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }
    }
}

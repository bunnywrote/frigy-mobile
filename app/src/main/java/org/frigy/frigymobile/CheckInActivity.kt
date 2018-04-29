package org.frigy.frigymobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class CheckInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)
    }

//    companion object {
//
//        fun newIntent(context: Context, user: User): Intent {
//            val intent = Intent(context, UserDetailActivity::class.java)
//            intent.putExtra(INTENT_USER_ID, user.id)
//            return intent
//        }
//    }
}

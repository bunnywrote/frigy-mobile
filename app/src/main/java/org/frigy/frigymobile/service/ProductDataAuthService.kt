package org.frigy.frigymobile.service

import android.app.Service
import android.content.Intent
import android.os.IBinder


class ProductDataAuthService : Service() {

    private var mAuthenticator: ProductDataAuthenticator? = null
    override fun onCreate() {
        // Create a new authenticator object
        mAuthenticator = ProductDataAuthenticator(this.applicationContext)
    }

    override fun onBind(intent: Intent): IBinder? {
        return mAuthenticator?.getIBinder()
    }

}
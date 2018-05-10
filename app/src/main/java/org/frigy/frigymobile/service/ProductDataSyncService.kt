package org.frigy.frigymobile.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ProductDataSyncService : Service() {

    private var sSyncAdapter: ProductDataSyncAdapter? = null

    private val sSyncAdapterLock = Any()

    override fun onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized(sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = ProductDataSyncAdapter(applicationContext, true)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return sSyncAdapter!!.getSyncAdapterBinder();
    }
}

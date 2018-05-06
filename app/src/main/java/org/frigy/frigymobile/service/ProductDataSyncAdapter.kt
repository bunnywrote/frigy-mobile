package org.frigy.frigymobile.service

import android.accounts.Account
import android.content.*
import android.os.Bundle
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import android.util.JsonReader


class ProductDataSyncAdapter(context: Context?, autoInitialize: Boolean)
    : AbstractThreadedSyncAdapter(context, autoInitialize) {
    var mContentResolver: ContentResolver = context!!.contentResolver

    constructor(context: Context?, autoInitialize: Boolean, allowParallelSyncs: Boolean) : this(context, autoInitialize)


    override fun onPerformSync(account: Account?, extras: Bundle, authority: String?, contentProviderClient: ContentProviderClient?, syncResult: SyncResult?) {
        //TODO: Implement API Call here..
    }


}
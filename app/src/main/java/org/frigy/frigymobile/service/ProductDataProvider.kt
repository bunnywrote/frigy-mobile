package org.frigy.frigymobile.service

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class ProductDataProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0

        //TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        return null
        //TODO("Implement this to handle requests for the MIME type of the data" + "at the given URI")
    }

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        return null
        //TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        return true
        //TODO("Implement this to initialize your content provider on startup.")
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
        //TODO("Implement this to handle query requests from clients.")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        return 0
        //TODO("Implement this to handle requests to update one or more rows.")
    }
}

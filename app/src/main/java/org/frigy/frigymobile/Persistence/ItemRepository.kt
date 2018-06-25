package org.frigy.frigymobile.Persistence

import android.content.Context
import android.os.AsyncTask
import org.frigy.frigymobile.Models.Item


class ItemRepository(context: Context) {

    private val mContext: Context = context

    private val mDB: FridgyInternalDatabase = FridgyInternalDatabase.getInstance(mContext)

    private val itemDao: ItemDao = mDB.itemDao()


/*    fun searchByBarcode(query: String): LiveData<List<Product>> {
        val resultData: MutableLiveData<List<Product>> = MutableLiveData()
        queryProductAPI(query, resultData)
        return resultData
    }*/

    fun insertAll(items: List<Item>) {
        InsertAllAsyncTask(itemDao).execute(items)
    }

    private class InsertAllAsyncTask internal constructor(private val mAsyncTaskDao: ItemDao) : AsyncTask<List<Item>, Void, Void>() {
        override fun doInBackground(vararg params: List<Item>): Void? {
            mAsyncTaskDao.insertAll(params[0])
            return null
        }
    }
}
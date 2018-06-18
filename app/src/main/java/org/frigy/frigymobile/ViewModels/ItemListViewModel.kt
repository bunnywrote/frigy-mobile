package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase


class ItemListViewModel(application: Application) : AndroidViewModel(application) {

    var items: LiveData<List<Item>>
    private val appDb: FridgyInternalDatabase

    init {
        appDb = FridgyInternalDatabase.getInstance(this.getApplication())
        items = appDb.itemDao().getAll()
    }

    fun getListItems(): LiveData<List<Item>> {
        return items
    }

//    fun addContact(contact: Contact) {
//        addAsynTask(appDb).execute(contact)
//    }
}
package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.FridgyInternalDatabase


class ItemListViewModel(application: Application) : AndroidViewModel(application) {

    var items: LiveData<List<Item>>
    val currentItem: MutableLiveData<Item> = MutableLiveData()

    private val appDb: FridgyInternalDatabase

    init {
        appDb = FridgyInternalDatabase.getInstance(this.getApplication())
        items = appDb.itemDao().getAll()
        currentItem.value = null
    }

    fun setCurrentItem(item: Item){
        this.currentItem.value = item
    }

    fun getListItems(): LiveData<List<Item>> {
        return items
    }

    fun getItem(itemId: Long): LiveData<Item>{
        return appDb.itemDao().getById(itemId)
    }
}
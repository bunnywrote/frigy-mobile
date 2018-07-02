package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Persistence.ItemRepository


class CheckOutBasketViewModel(app: Application) : AndroidViewModel(app) {

    val mBasketItems: MutableLiveData<MutableList<Item>> = MutableLiveData()
    private val itemRepository = ItemRepository(getApplication())

    init {
        mBasketItems.value = mutableListOf();
    }

    fun getBasketItems(): MutableLiveData<MutableList<Item>>{
        return mBasketItems
    }

    fun getAll():LiveData<List<Item>>{
        return itemRepository.getListItems()
    }

    fun addItem(item: Item){
        mBasketItems.value?.add(item)
    }

    fun removeItem(item: Item){
        mBasketItems.value?.remove(item)
    }

    fun takeItems(){

    }
}
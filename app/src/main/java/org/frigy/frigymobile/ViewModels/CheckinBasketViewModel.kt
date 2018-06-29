package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.Persistence.ItemRepository


class CheckinBasketViewModel(app: Application) : AndroidViewModel(app) {

    val mBasketItems: MutableLiveData<MutableList<Item>> = MutableLiveData()

    private val itemRepository = ItemRepository(getApplication())

    //private var mCurrentItem: Item? = null;

    init {
        mBasketItems.value = mutableListOf();
    }

    fun createItemFromProduct(product: Product) {

        val basketItems: MutableList<Item>? = mBasketItems.value;

        val newItem = Item(product)
        basketItems!!.add(newItem)

        mBasketItems.value = basketItems
    }

    fun removeItem(item: Item) {
        val basketItems: MutableList<Item>? = mBasketItems.value;

        if (!basketItems!!.isEmpty()) {
            basketItems.remove(item)
        }

        mBasketItems.value = basketItems
    }

    fun commit() {
        if (!mBasketItems.value!!.isEmpty()) {
            itemRepository.insertAll(mBasketItems.value!!.toList())
            mBasketItems.value = mutableListOf()
        }
    }
}
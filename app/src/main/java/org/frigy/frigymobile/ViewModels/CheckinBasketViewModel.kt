package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product


class CheckinBasketViewModel(app: Application) : AndroidViewModel(app) {

    val mBasketItems: MutableLiveData<MutableList<Item>> = MutableLiveData()

    private var mCurrentItem: Item? = null;

    fun createItemFromProduct(product: Product) {

        var basketItems: MutableList<Item>? = mBasketItems.value;

        if (basketItems == null) {
            basketItems = mutableListOf()
        }

        val newItem = Item(product)
        basketItems.add(newItem)

        mBasketItems.value = basketItems
    }

    fun removeItem(item: Item) {
        val basketItems: MutableList<Item>? = mBasketItems.value;

        if (basketItems != null) {
            basketItems.remove(item)
        }

        mBasketItems.value = basketItems
    }

    fun commit() {
        //TODO save items in DB
    }


}
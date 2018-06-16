package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product


class CheckinBasketModel(app: Application) : AndroidViewModel(app) {

    val mBasketItems: MutableLiveData<MutableList<Item>> = MutableLiveData()

    private var mCurrentItem: Item? = null;

    fun createItemFromProduct(product: Product) {

        var basketItems: MutableList<Item>? = mBasketItems.value;

        if (basketItems == null) {
            basketItems = mutableListOf()
        }

        val newItem = Item(product)
        basketItems.add(newItem)

        mBasketItems.postValue(basketItems)
    }

    fun removeItem(item: Item) {
        var basketItems: MutableList<Item>? = mBasketItems.value;

        if (basketItems != null) {
            basketItems.remove(item)
        }

    }


}
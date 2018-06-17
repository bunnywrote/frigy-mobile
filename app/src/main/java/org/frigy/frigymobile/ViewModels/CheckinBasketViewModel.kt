package org.frigy.frigymobile.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.frigy.frigymobile.BR
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product
import org.frigy.frigymobile.R


class CheckinBasketViewModel() {

    val basketItems: ObservableList<Item> = ObservableArrayList<Item>()
    val basketItemBinding: ItemBinding<Item> = ItemBinding.of<Item> { itemBinding, position, item ->
        itemBinding.set(BR.basketItem, R.layout.checkin_basket_item_layout)
                .bindExtra(BR.itemTitle, item.product.title)
                .bindExtra(BR.itemInfo, item.created.toString())
    }

    private var mCurrentItem: Item? = null;

    fun createItemFromProduct(product: Product) {

/*        var basketItems: MutableList<Item>? = basketItems

        if (basketItems == null) {
            basketItems = mutableListOf()
        }

        val newItem = Item(product)
        basketItems.add(newItem)

        mBasketItems.postValue(basketItems)*/
    }

    fun removeItem(item: Item) {
/*        var basketItems: MutableList<Item>? = mBasketItems.value;

        if (basketItems != null) {
            basketItems.remove(item)
        }*/

    }


}
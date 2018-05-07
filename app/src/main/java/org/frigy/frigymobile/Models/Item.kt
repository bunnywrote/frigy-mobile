package org.frigy.frigymobile.Models

import java.time.LocalDate

enum class ItemState{
    GOOD, BAD
}

class Item {
    constructor(title: String){
        product = Product();
        product.title = title;
        created = LocalDate.now()
    }
    var state: ItemState = ItemState.GOOD

    lateinit var product: Product
    lateinit var created: LocalDate
    lateinit var removed: LocalDate
    lateinit var fridge: Fridge
    lateinit var itemLogEntry: ItemLogEntry
}
package org.frigy.frigymobile.Models

import java.time.LocalDate
import java.util.*

enum class ItemState{
    GOOD, BAD
}

class Item {
    constructor(title: String){
        product = Product();
        product.title = title;
        created = Date()
    }
    var state: ItemState = ItemState.GOOD

    lateinit var product: Product
    lateinit var created: Date
    lateinit var removed: LocalDate
    lateinit var fridge: Fridge
    lateinit var itemLogEntry: ItemLogEntry
}
package org.frigy.frigymobile.Models

import java.util.*

enum class ItemState{
    GOOD, BAD
}

class Item {
    var state: ItemState = ItemState.GOOD

    lateinit var product: Product
    lateinit var created: Date
    lateinit var removed: Date
    lateinit var fridge: Fridge
    lateinit var itemLogEntry: ItemLogEntry
}
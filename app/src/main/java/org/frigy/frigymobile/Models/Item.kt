package org.frigy.frigymobile.Models

import java.util.*

enum class ItemState{
    GOOD, BAD
}

class Item {
    lateinit var product: Product
    lateinit var created: Date
    lateinit var removed: Date
    var state: ItemState = ItemState.GOOD
}
package org.frigy.frigymobile.Models

import java.util.*


enum class EntryType{
    INFO, WARNING, ERROR
}

class ItemLogEntry {
    var quantity: Double = 0.0

    lateinit var quantityUnit: QuantityUnit
    lateinit var message: String
    lateinit var timestamp: Date
    lateinit var entryType: EntryType
    lateinit var user: User
}
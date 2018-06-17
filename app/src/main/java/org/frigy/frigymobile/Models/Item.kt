package org.frigy.frigymobile.Models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.util.*

@Entity(tableName = "items")
class Item(

        @Embedded
        @NonNull
        val product: Product,

        val created: Date
) {

    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0

    @Ignore
    constructor(product: Product) : this(product, Date())

    constructor(product: Product, date: Date, id: Long) : this(product, date) {
        itemId = id;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (product != other.product) return false
        if (created != other.created) return false
        if (itemId != other.itemId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = product.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + itemId.hashCode()
        return result
    }


//
//enum class ItemState{
//    GOOD, BAD
//}

//    var state: ItemState = ItemState.GOOD
//    lateinit var removed: Date
//    lateinit var itemLogEntry: ItemLogEntry
//
//    @Ignore
//    lateinit var fridge: Fridge
}
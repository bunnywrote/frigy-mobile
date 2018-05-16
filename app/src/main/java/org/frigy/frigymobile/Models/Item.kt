package org.frigy.frigymobile.Models

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.util.*
//
//enum class ItemState{
//    GOOD, BAD
//}

@Entity(tableName = "items")
class Item(
        @PrimaryKey(autoGenerate = true)
        val itemId: Int?,

        @Embedded
        @NonNull
        var product: Product,

        val created: Date

//        var state: ItemState = ItemState.GOOD,

){
//    constructor(title: String){
//        product = Product()
//        product.title = title
//        created = Date()
//    }
//    @PrimaryKey(autoGenerate = true)
//    var itemId: Long = 0



//    var state: ItemState = ItemState.GOOD
//    lateinit var created: Date
//    lateinit var removed: Date
//    lateinit var itemLogEntry: ItemLogEntry
//
//    @Ignore
//    lateinit var fridge: Fridge
}
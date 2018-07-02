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

    var isSelected: Boolean = false


    @Ignore
    constructor(product: Product) : this(product, Date())

    constructor(product: Product, date: Date, id: Long) : this(product, date) {
        itemId = id;
    }
}
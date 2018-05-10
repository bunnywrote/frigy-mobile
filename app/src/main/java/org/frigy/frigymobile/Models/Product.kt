package org.frigy.frigymobile.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
class Product{

    @PrimaryKey
    var id: Long = 0
        get() = field
        set(value) {
            field = value
        }
    var code: Long = 0
        get() = field
        set(value) {
            field = value
        }
    var quantity: Double = 0.0
        get() = field
        set(value) {
            field = value
        }

    var generic: Boolean = false
        get() = field
        set(value) {
            field = value
        }

    lateinit var quantityUnit: QuantityUnit
    lateinit var title: String
    lateinit var ingredients: String
    lateinit var imageSrc: String

    @Ignore
    lateinit var category: Category

}
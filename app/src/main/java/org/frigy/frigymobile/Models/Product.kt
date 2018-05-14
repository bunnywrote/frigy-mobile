package org.frigy.frigymobile.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
        @PrimaryKey(autoGenerate = true) val productId: Int?,
        var title: String
){
//
//    @PrimaryKey
//    var id: Long = 0
//
//    var code: Long = 0
//
//    var quantity: Double = 0.0
//
//    var generic: Boolean = false
//
//    lateinit var quantityUnit: QuantityUnit
//    lateinit var title: String
//    lateinit var ingredients: String
//    lateinit var imageSrc: String
//
//    @Ignore
//    lateinit var category: Category

}
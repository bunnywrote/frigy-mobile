package org.frigy.frigymobile.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import org.frigy.frigymobile.Models.dto.FoodrepoProduct

@Entity
class Product() {

    @PrimaryKey(autoGenerate = true)

    @NonNull
    var id: Long = 0

    var code: Long = 0

    var quantity: Double = 0.0

    var generic: Boolean = false

    //lateinit var quantityUnit: QuantityUnit
    lateinit var title: String
    var ingredients: String? = null

    @Ignore
    lateinit var imageSrc: String

    @Ignore
    lateinit var category: Category

    constructor(foodrepoProduct: FoodrepoProduct) : this() {
        code = foodrepoProduct.barcode.toLong()
        quantity = foodrepoProduct.quantity
        //quantityUnit = QuantityUnit.getBySign(foodrepoProduct.unit)
        ingredients = foodrepoProduct.ingredientTranslations.de
        title = foodrepoProduct.nameTranslations.de
    }

    override fun toString(): String {
        return "Product(id=$id, code=$code, quantity=$quantity, generic=$generic, title='$title', ingredients='$ingredients')"
    }


}
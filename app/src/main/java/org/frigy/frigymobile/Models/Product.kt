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

    lateinit var quantityUnit: QuantityUnit
    lateinit var title: String
    var ingredients: String? = null

    @Ignore
    var imageSrc: String? = null

    @Ignore
    var category: Category? = null

    constructor(foodrepoProduct: FoodrepoProduct) : this() {
        code = foodrepoProduct.barcode.toLong()
        quantity = foodrepoProduct.quantity
        quantityUnit = QuantityUnit.getBySign(foodrepoProduct.unit)
        ingredients = foodrepoProduct.ingredientTranslations.de
        title = foodrepoProduct.nameTranslations.de
        imageSrc = foodrepoProduct.images.find { image -> image.categories.contains("Front") }!!.thumb
    }

    override fun toString(): String {
        return "Product(id=$id, code=$code, quantity=$quantity, generic=$generic, quantityUnit=$quantityUnit, title='$title', ingredients=$ingredients, imageSrc='$imageSrc', category=$category)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + code.hashCode()
        return result
    }


}
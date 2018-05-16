package org.frigy.frigymobile.Models.dto

import com.google.gson.annotations.SerializedName


class FoodrepoProduct {
    var id: Long = 0
    var barcode: String = ""
    var quantity: Double = 0.0

    lateinit var unit: String

    @SerializedName("name_translations")
    lateinit var nameTranslations: NameTranslations

    @SerializedName("ingredients_translations")
    lateinit var ingredientTranslations: IngredientTranslations
    //lateinit var imageSrc: String
    //lateinit var category: Category

    lateinit var images: Array<Image>

    class NameTranslations {
        var de: String = ""
    }

    class IngredientTranslations {
        var de: String = ""
    }

    class Image {
        var categories: Array<String> = arrayOf(String())
        var thumb: String = ""
        var medium: String = ""
        var large: String = ""
        var xlarge: String = ""
    }

    override fun toString(): String {
        return "Product(id=$id, barcode='$barcode', quantity=$quantity, quantityUnit=$unit, title='${nameTranslations.de}', ingredients='${ingredientTranslations.de}')"
    }

}
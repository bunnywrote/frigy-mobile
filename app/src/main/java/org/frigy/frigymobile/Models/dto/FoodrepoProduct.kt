package org.frigy.frigymobile.Models.dto

import com.google.gson.annotations.SerializedName


class FoodrepoProduct {
    var id: Long = 0
    var barcode: String = ""
    var quantity: Double = 0.0

    val UNKNOWN: String = "unknown"

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
        var fr: String = ""
        var en: String = ""
        var it: String = ""
    }

    class IngredientTranslations {
        var de: String = ""
        var fr: String = ""
        var en: String = ""
        var it: String = ""
    }

    class Image {
        var categories: Array<String> = arrayOf(String())
        var thumb: String = ""
        var medium: String = ""
        var large: String = ""
        var xlarge: String = ""
    }

    fun getNameTranslation(): String {
        var nameTranslation = checkTranslation(nameTranslations.de)
        if (nameTranslation != UNKNOWN) return nameTranslation

        nameTranslation = checkTranslation(nameTranslations.fr)
        if (nameTranslation != UNKNOWN) return nameTranslation

        nameTranslation = checkTranslation(nameTranslations.it)
        if (nameTranslation != UNKNOWN) return nameTranslation

        nameTranslation = checkTranslation(nameTranslations.en)
        if (nameTranslation != UNKNOWN) return nameTranslation

        return nameTranslation
    }

    private fun checkTranslation(translation: String): String {
        if (translation != null && !translation.isEmpty()) {
            return translation;
        }
        return UNKNOWN
    }

    override fun toString(): String {
        return "Product(id=$id, barcode='$barcode', quantity=$quantity, quantityUnit=$unit, title='${nameTranslations.de}', ingredients='${ingredientTranslations.de}')"
    }

}
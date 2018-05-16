package org.frigy.frigymobile.Models


enum class QuantityUnit(sign: String, displayName: String) {

    GRAM("g", "Gramm"),
    KILOGRAM("kg", "Kilogramm"),
    MILLILITER("ml", "Milliliter"),
    LITER("l", "Liter"),
    PIECE("pcs", "Stück");

    val sign: String = sign
    val displayNameDE: String = displayName

    companion object {
        fun getBySign(sign: String): QuantityUnit {
            return QuantityUnit.values().find { it.sign == (sign) }!!
        }
    }

    override fun toString(): String {
        return "QuantityUnit(sign='$sign', displayNameDE='$displayNameDE')"
    }


}
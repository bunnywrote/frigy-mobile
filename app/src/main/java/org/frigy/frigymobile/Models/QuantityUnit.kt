package org.frigy.frigymobile.Models


enum class QuantityUnit(sign: String, displayName: String) {

    GRAM("g", "Gramm"),
    MILLILITER("ml", "Milliliter"),
    PIECE("pcs", "Stück");

    private val sign: String = sign
    private val displayName: String = displayName

    companion object {
        fun getBySign(sign: String): QuantityUnit {
            return QuantityUnit.values().find { it.sign == (sign) }!!
        }
    }

}
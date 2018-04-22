package org.frigy.frigymobile.Models


class Product{
    var id: Long = 0
    var code: Long = 0
    var quantity: Double = 0.0
    var generic: Boolean = false

    lateinit var quantityUnit: QuantityUnit
    lateinit var title: String
    lateinit var ingredients: String
    lateinit var imageSrc: String
    lateinit var category: Category

}
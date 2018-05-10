package org.frigy.frigymobile.Extensions

import java.text.SimpleDateFormat
import java.util.*


class DateTimeExtensions {

    companion object {
        fun toSimpleString(date: Date) : String{
            val format = SimpleDateFormat("dd.MM.yyy")
            return format.format(date)
        }
    }
}
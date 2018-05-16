package org.frigy.frigymobile.Converters

import android.arch.persistence.room.TypeConverter
import org.frigy.frigymobile.Models.QuantityUnit
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toQuantityUnit(sign: String): QuantityUnit? {
        return QuantityUnit.getBySign(sign)
    }

    @TypeConverter
    fun toQuantitySign(quantityUnit: QuantityUnit): String {
        return quantityUnit.sign
    }
}

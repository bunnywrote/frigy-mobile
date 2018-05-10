package org.frigy.frigymobile.Persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import org.frigy.frigymobile.Models.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class FridgyInternalDatabase : RoomDatabase() {
    abstract fun userDao(): ProductDao
}
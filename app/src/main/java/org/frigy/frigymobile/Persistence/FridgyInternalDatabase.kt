package org.frigy.frigymobile.Persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import org.frigy.frigymobile.Models.Item
import org.frigy.frigymobile.Models.Product

@Database(entities = [(Product::class), (Item::class)], version = 1)
abstract class FridgyInternalDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun itemDao(): ItemDao

}
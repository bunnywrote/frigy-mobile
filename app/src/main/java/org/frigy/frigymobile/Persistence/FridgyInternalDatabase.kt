package org.frigy.frigymobile.Persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.frigy.frigymobile.Models.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class FridgyInternalDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var INSTANCE: FridgyInternalDatabase? = null

        fun getInstance(context: Context): FridgyInternalDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        FridgyInternalDatabase::class.java, "fridgy-internal-database").allowMainThreadQueries().build()
    }
}
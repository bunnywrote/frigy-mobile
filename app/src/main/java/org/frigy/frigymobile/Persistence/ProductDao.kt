package org.frigy.frigymobile.Persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import org.frigy.frigymobile.Models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<Product>
}
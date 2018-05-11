package org.frigy.frigymobile.Persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import org.frigy.frigymobile.Models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>

    @Insert
    fun insert(product: Product)

    @Query("SELECT * FROM Product WHERE product.code = :barcode")
    fun searchProductsByBarcode(barcode: String): List<Product>

}
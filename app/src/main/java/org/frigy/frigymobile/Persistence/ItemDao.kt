package org.frigy.frigymobile.Persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import org.frigy.frigymobile.Models.Item

@Dao
interface ItemDao {

    @Insert
    fun insert(item: Item)

    @Query("SELECT * FROM items")
    fun getAll(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE items.itemId = :itemId")
    fun getById(itemId: Long): LiveData<Item>
}
package org.frigy.frigymobile.Persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import org.frigy.frigymobile.Models.Item

@Dao
interface ItemDao {

    @Insert
    fun insert(repo: Item)

    @Query("SELECT * FROM items")
    fun getAll(): List<Item>

}
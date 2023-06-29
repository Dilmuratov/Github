package com.example.github.data.local.dao

import androidx.room.*
import androidx.room.Dao
import com.example.github.data.models.HistoryOfSearchData

@Dao
interface Dao {

    @Query("SELECT * FROM history")
    suspend fun getAllData(): List<HistoryOfSearchData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(data: HistoryOfSearchData)

    @Delete
    suspend fun deleteData(data: HistoryOfSearchData)
}
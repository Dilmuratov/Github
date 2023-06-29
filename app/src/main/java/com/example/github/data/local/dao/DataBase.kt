package com.example.github.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.github.data.models.HistoryOfSearchData

@Database(
    entities = [HistoryOfSearchData::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        const val dataBaseName = "db_name"
    }
}
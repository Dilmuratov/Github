package com.example.github.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryOfSearchData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val query: String
)

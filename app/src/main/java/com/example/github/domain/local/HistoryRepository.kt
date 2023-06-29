package com.example.github.domain.local

import com.example.github.data.models.HistoryOfSearchData
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun getAllData(): Flow<List<HistoryOfSearchData>>

    suspend fun addData(data: HistoryOfSearchData)

    suspend fun deleteData(data: HistoryOfSearchData)
}
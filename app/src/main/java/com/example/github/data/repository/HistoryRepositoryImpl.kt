package com.example.github.data.repository

import com.example.github.data.local.dao.Dao
import com.example.github.data.models.HistoryOfSearchData
import com.example.github.domain.local.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(private val dao: Dao) : HistoryRepository {

    override suspend fun getAllData(): Flow<List<HistoryOfSearchData>> = flow {
        emit(dao.getAllData())
    }

    override suspend fun addData(data: HistoryOfSearchData)  = dao.addData(data)

    override suspend fun deleteData(data: HistoryOfSearchData) = dao.deleteData(data)
}
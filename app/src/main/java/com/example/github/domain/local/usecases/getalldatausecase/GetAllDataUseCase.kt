package com.example.github.domain.local.usecases.getalldatausecase

import com.example.github.data.models.HistoryOfSearchData
import kotlinx.coroutines.flow.Flow

interface GetAllDataUseCase {

    suspend fun execute(): Flow<List<HistoryOfSearchData>>
}
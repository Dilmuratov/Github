package com.example.github.domain.local.usecases.deletedatausecase

import com.example.github.data.models.HistoryOfSearchData
import com.example.github.domain.local.HistoryRepository

class DeleteDataUseCaseImpl(private val historyRepository: HistoryRepository) : DeleteDataUseCase {

    override suspend fun execute(data: HistoryOfSearchData) = historyRepository.deleteData(data)
}
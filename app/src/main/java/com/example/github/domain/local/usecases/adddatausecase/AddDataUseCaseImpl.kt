package com.example.github.domain.local.usecases.adddatausecase

import com.example.github.data.models.HistoryOfSearchData
import com.example.github.domain.local.HistoryRepository

class AddDataUseCaseImpl(private val historyRepository: HistoryRepository) : AddDataUseCase {

    override suspend fun execute(data: HistoryOfSearchData) = historyRepository.addData(data)

}
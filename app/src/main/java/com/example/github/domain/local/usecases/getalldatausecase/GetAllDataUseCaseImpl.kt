package com.example.github.domain.local.usecases.getalldatausecase

import com.example.github.domain.local.HistoryRepository

class GetAllDataUseCaseImpl(private val historyRepository: HistoryRepository) : GetAllDataUseCase {

    override suspend fun execute() = historyRepository.getAllData()

}
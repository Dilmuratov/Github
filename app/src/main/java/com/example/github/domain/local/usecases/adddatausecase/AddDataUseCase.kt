package com.example.github.domain.local.usecases.adddatausecase

import com.example.github.data.models.HistoryOfSearchData

interface AddDataUseCase {

    suspend fun execute(data: HistoryOfSearchData)
}
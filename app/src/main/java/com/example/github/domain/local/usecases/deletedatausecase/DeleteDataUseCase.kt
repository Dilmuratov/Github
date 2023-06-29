package com.example.github.domain.local.usecases.deletedatausecase

import com.example.github.data.models.HistoryOfSearchData

interface DeleteDataUseCase {

    suspend fun execute(data: HistoryOfSearchData)
}
package com.example.github.domain.usecases.getaccesstokenusecase

import com.example.github.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetAccessTokenUseCase {

    suspend fun execute(): Flow<ResultData<String>>

}
package com.example.github.domain.usecases.getaccesstokenusecase

import com.example.github.data.models.ResultData
import com.example.github.domain.MainRepository
import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCase
import kotlinx.coroutines.flow.Flow

class GetAccessTokenUseCaseImpl(
    private val mainRepository: MainRepository
) : GetAccessTokenUseCase {

    override suspend fun execute(): Flow<ResultData<String>> = mainRepository.getAccessToken()
}
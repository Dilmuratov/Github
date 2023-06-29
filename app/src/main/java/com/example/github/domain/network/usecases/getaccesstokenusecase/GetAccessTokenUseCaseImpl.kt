package com.example.github.domain.network.usecases.getaccesstokenusecase

import com.example.github.data.models.ResultData
import com.example.github.domain.network.MainRepository
import kotlinx.coroutines.flow.Flow

class GetAccessTokenUseCaseImpl(
    private val mainRepository: MainRepository
) : GetAccessTokenUseCase {

    override suspend fun execute(): Flow<ResultData<String>> = mainRepository.getAccessToken()
}
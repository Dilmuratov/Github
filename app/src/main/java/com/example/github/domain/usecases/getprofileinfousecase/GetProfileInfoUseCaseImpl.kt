package com.example.github.domain.usecases.getprofileinfousecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import com.example.github.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetProfileInfoUseCaseImpl(
    private val mainRepository: MainRepository
) : GetProfileInfoUseCase {

    override suspend fun execute(): Flow<ResultData<UserProfileInfoResponseData>> =
        mainRepository.getUserProfileInfo()

}
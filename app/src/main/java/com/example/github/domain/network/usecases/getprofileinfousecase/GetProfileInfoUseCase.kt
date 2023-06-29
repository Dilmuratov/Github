package com.example.github.domain.network.usecases.getprofileinfousecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import kotlinx.coroutines.flow.Flow

interface GetProfileInfoUseCase {
    suspend fun execute(): Flow<ResultData<UserProfileInfoResponseData>>
}
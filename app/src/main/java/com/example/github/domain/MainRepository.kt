package com.example.github.domain

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAccessToken(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<UserProfileInfoResponseData>>

    suspend fun getUserRepositories(): Flow<ResultData<List<RepositoryData>>>

}
package com.example.github.domain.network

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getAccessToken(): Flow<ResultData<String>>

    suspend fun getUserProfileInfo(): Flow<ResultData<UserProfileInfoResponseData>>

    suspend fun getUserRepositories(): Flow<ResultData<List<RepositoryData>>>

    suspend fun searchRepositories(q: String): Flow<ResultData<SearchResponseData<RepositoryItem>>>

    suspend fun searchUsers(q: String): Flow<ResultData<SearchResponseData<UserItem>>>

}
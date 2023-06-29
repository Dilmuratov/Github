package com.example.github.domain.network.usecases.searchusersusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
import kotlinx.coroutines.flow.Flow

interface SearchUsersUseCase {

    suspend fun execute(q: String): Flow<ResultData<SearchResponseData<UserItem>>>
}
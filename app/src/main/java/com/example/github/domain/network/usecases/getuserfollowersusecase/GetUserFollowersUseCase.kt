package com.example.github.domain.network.usecases.getuserfollowersusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserfollowers.FollowerItem
import kotlinx.coroutines.flow.Flow

interface GetUserFollowersUseCase {

    suspend fun execute(login: String, type: String): Flow<ResultData<List<FollowerItem>>>
}
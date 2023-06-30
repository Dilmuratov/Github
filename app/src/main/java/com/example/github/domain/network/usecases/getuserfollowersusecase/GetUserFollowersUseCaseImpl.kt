package com.example.github.domain.network.usecases.getuserfollowersusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserfollowers.FollowerItem
import com.example.github.domain.network.MainRepository
import kotlinx.coroutines.flow.Flow

class GetUserFollowersUseCaseImpl(private val mainRepository: MainRepository) :
    GetUserFollowersUseCase {

    override suspend fun execute(login: String, type: String): Flow<ResultData<List<FollowerItem>>> =
        mainRepository.getUserFollowers(login, type)
}
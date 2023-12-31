package com.example.github.domain.network.usecases.getuserrepositoriesusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.domain.network.MainRepository
import kotlinx.coroutines.flow.Flow

class GetUserRepositoriesUseCaseImpl(private val mainRepository: MainRepository) :
    GetUserRepositoriesUseCase {

    override suspend fun execute(): Flow<ResultData<List<RepositoryData>>> =
        mainRepository.getUserRepositories()
}
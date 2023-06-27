package com.example.github.domain.usecases.getuserrepositoriesusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import kotlinx.coroutines.flow.Flow

interface GetUserRepositoriesUseCase {

    suspend fun execute(): Flow<ResultData<List<RepositoryData>>>

}
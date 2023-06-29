package com.example.github.domain.usecases.searchrepositoriesusecase

import com.example.github.data.models.ResultData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import kotlinx.coroutines.flow.Flow

interface SearchRepositoriesUseCase {

    suspend fun execute(q: String): Flow<ResultData<SearchResponseData<RepositoryItem>>>

}
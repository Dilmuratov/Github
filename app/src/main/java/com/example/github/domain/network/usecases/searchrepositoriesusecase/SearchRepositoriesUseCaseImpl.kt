package com.example.github.domain.network.usecases.searchrepositoriesusecase

import com.example.github.domain.network.MainRepository

class SearchRepositoriesUseCaseImpl(
    private val mainRepository: MainRepository
) : SearchRepositoriesUseCase {

    override suspend fun execute(q: String) = mainRepository.searchRepositories(q)

}
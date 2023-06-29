package com.example.github.domain.usecases.searchrepositoriesusecase

import com.example.github.domain.MainRepository

class SearchRepositoriesUseCaseImpl(
    private val mainRepository: MainRepository
) : SearchRepositoriesUseCase {

    override suspend fun execute(q: String) = mainRepository.searchRepositories(q)

}
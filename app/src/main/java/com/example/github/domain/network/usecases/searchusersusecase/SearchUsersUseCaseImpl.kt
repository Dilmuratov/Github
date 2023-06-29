package com.example.github.domain.network.usecases.searchusersusecase

import com.example.github.domain.network.MainRepository

class SearchUsersUseCaseImpl(
    private val mainRepository: MainRepository
) : SearchUsersUseCase {

    override suspend fun execute(q: String) = mainRepository.searchUsers(q)
}
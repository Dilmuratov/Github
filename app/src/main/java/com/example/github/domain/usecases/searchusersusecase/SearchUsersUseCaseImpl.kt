package com.example.github.domain.usecases.searchusersusecase

import com.example.github.domain.MainRepository

class SearchUsersUseCaseImpl(
    private val mainRepository: MainRepository
) : SearchUsersUseCase {

    override suspend fun execute(q: String) = mainRepository.searchUsers(q)
}
package com.example.github.di

import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCase
import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCaseImpl
import com.example.github.domain.usecases.getprofileinfousecase.GetProfileInfoUseCase
import com.example.github.domain.usecases.getprofileinfousecase.GetProfileInfoUseCaseImpl
import com.example.github.domain.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCase
import com.example.github.domain.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCaseImpl
import com.example.github.domain.usecases.searchrepositoriesusecase.SearchRepositoriesUseCase
import com.example.github.domain.usecases.searchrepositoriesusecase.SearchRepositoriesUseCaseImpl
import com.example.github.domain.usecases.searchusersusecase.SearchUsersUseCase
import com.example.github.domain.usecases.searchusersusecase.SearchUsersUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetAccessTokenUseCase> {
        GetAccessTokenUseCaseImpl(get())
    }

    factory<GetProfileInfoUseCase> {
        GetProfileInfoUseCaseImpl(get())
    }

    factory<GetUserRepositoriesUseCase> {
        GetUserRepositoriesUseCaseImpl(get())
    }

    factory<SearchRepositoriesUseCase> {
        SearchRepositoriesUseCaseImpl(get())
    }

    factory<SearchUsersUseCase> {
        SearchUsersUseCaseImpl(get())
    }
}
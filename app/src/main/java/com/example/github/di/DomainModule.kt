package com.example.github.di

import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCase
import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCaseImpl
import com.example.github.domain.usecases.getprofileinfousecase.GetProfileInfoUseCase
import com.example.github.domain.usecases.getprofileinfousecase.GetProfileInfoUseCaseImpl
import com.example.github.domain.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCase
import com.example.github.domain.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCaseImpl
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
}
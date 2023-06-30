package com.example.github.di

import com.example.github.domain.local.usecases.adddatausecase.AddDataUseCase
import com.example.github.domain.local.usecases.adddatausecase.AddDataUseCaseImpl
import com.example.github.domain.local.usecases.deletedatausecase.DeleteDataUseCase
import com.example.github.domain.local.usecases.deletedatausecase.DeleteDataUseCaseImpl
import com.example.github.domain.local.usecases.getalldatausecase.GetAllDataUseCase
import com.example.github.domain.local.usecases.getalldatausecase.GetAllDataUseCaseImpl
import com.example.github.domain.network.usecases.getaccesstokenusecase.GetAccessTokenUseCase
import com.example.github.domain.network.usecases.getaccesstokenusecase.GetAccessTokenUseCaseImpl
import com.example.github.domain.network.usecases.getprofileinfousecase.GetProfileInfoUseCase
import com.example.github.domain.network.usecases.getprofileinfousecase.GetProfileInfoUseCaseImpl
import com.example.github.domain.network.usecases.getuserfollowersusecase.GetUserFollowersUseCase
import com.example.github.domain.network.usecases.getuserfollowersusecase.GetUserFollowersUseCaseImpl
import com.example.github.domain.network.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCase
import com.example.github.domain.network.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCaseImpl
import com.example.github.domain.network.usecases.searchrepositoriesusecase.SearchRepositoriesUseCase
import com.example.github.domain.network.usecases.searchrepositoriesusecase.SearchRepositoriesUseCaseImpl
import com.example.github.domain.network.usecases.searchusersusecase.SearchUsersUseCase
import com.example.github.domain.network.usecases.searchusersusecase.SearchUsersUseCaseImpl
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

    factory<GetAllDataUseCase> {
        GetAllDataUseCaseImpl(get())
    }

    factory<AddDataUseCase> {
        AddDataUseCaseImpl(get())
    }

    factory<DeleteDataUseCase> {
        DeleteDataUseCaseImpl(get())
    }

    factory<GetUserFollowersUseCase> {
        GetUserFollowersUseCaseImpl(get())
    }
}
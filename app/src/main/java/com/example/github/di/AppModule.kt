package com.example.github.di

import com.example.github.presentation.HistoryViewModel
import com.example.github.presentation.HistoryViewModelImpl
import com.example.github.presentation.MainViewModel
import com.example.github.presentation.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModelImpl(
            getAccessTokenUseCase = get(),
            getProfileInfoUseCase = get(),
            getUserRepositoriesUseCase = get(),
            searchRepositoriesUseCase = get(),
            searchUsersUseCase = get(),
            getUserFollowersUseCase = get()
        )
    }

    viewModel<HistoryViewModel> {
        HistoryViewModelImpl(
            getAllDataUseCase = get(),
            addDataUseCase = get(),
            deleteDataUseCase = get()
        )
    }
}
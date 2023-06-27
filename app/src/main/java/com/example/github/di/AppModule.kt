package com.example.github.di

import com.example.github.presentation.MainViewModel
import com.example.github.presentation.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModelImpl(
            getAccessTokenUseCase = get(),
            getProfileInfoUseCase = get(),
            getUserRepositoriesUseCase = get()
        )
    }
}
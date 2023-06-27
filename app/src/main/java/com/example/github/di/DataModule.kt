package com.example.github.di

import com.example.github.domain.MainRepository
import com.example.github.data.repository.MainRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single <MainRepository> {
        MainRepositoryImpl()
    }
}
package com.example.github.di

import androidx.room.Room
import com.example.github.data.local.dao.DataBase
import com.example.github.data.repository.HistoryRepositoryImpl
import com.example.github.data.repository.MainRepositoryImpl
import com.example.github.domain.local.HistoryRepository
import com.example.github.domain.network.MainRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl()
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(dao = get())
    }

    single<DataBase> {
        Room.databaseBuilder(
            androidContext(),
            DataBase::class.java,
            DataBase.dataBaseName
        ).fallbackToDestructiveMigration().build()
    }
}
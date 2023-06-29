package com.example.github.di

import com.example.github.data.local.dao.Dao
import com.example.github.data.local.dao.DataBase
import org.koin.dsl.module

val localModule = module {
    single<Dao> {
        get<DataBase>().getDao()
    }
}
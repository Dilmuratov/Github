package com.example.github.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.github.data.models.HistoryOfSearchData

abstract class HistoryViewModel : ViewModel() {

    abstract val allDataLiveData: LiveData<List<HistoryOfSearchData>>

    abstract suspend fun getAllData()

    abstract suspend fun addData(data: HistoryOfSearchData)

    abstract suspend fun deleteData(data: HistoryOfSearchData)
}
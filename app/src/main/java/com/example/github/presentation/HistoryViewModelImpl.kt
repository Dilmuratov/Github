package com.example.github.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.github.data.models.HistoryOfSearchData
import com.example.github.domain.local.usecases.adddatausecase.AddDataUseCase
import com.example.github.domain.local.usecases.deletedatausecase.DeleteDataUseCase
import com.example.github.domain.local.usecases.getalldatausecase.GetAllDataUseCase

class HistoryViewModelImpl(
    private val getAllDataUseCase: GetAllDataUseCase,
    private val addDataUseCase: AddDataUseCase,
    private val deleteDataUseCase: DeleteDataUseCase
) : HistoryViewModel() {
    private val _allDataLiveData = MutableLiveData<List<HistoryOfSearchData>>()
    override val allDataLiveData: LiveData<List<HistoryOfSearchData>>
        get() = _allDataLiveData

    override suspend fun getAllData() {
        getAllDataUseCase.execute().collect {
            _allDataLiveData.value = it
        }
    }

    override suspend fun addData(data: HistoryOfSearchData) = addDataUseCase.execute(data)

    override suspend fun deleteData(data: HistoryOfSearchData) = deleteDataUseCase.execute(data)
}
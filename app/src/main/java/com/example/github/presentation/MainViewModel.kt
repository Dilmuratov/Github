package com.example.github.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData

abstract class MainViewModel : ViewModel() {

    abstract val getAccessTokenLiveData: LiveData<String?>
    abstract val getUserRepositoriesLiveData: LiveData<List<RepositoryData>?>
    abstract val messageLiveData: LiveData<String>
    abstract val errorLiveData: LiveData<Throwable>
    abstract val getUserProfileLiveData: LiveData<UserProfileInfoResponseData?>

    abstract suspend fun getAccessToken()

    abstract suspend fun getUserProfileInfo()

    abstract suspend fun getUserRepositories()

}
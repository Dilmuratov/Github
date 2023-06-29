package com.example.github.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData

abstract class MainViewModel : ViewModel() {

    abstract val getAccessTokenLiveData: LiveData<String?>
    abstract val getUserRepositoriesLiveData: LiveData<List<RepositoryData>?>
    abstract val searchRepositoriesLiveData: LiveData<SearchResponseData<RepositoryItem>?>
    abstract val searchUsersLiveData: LiveData<SearchResponseData<UserItem>?>
    abstract val messageLiveData: LiveData<String>
    abstract val errorLiveData: LiveData<Throwable>
    abstract val getUserProfileLiveData: LiveData<UserProfileInfoResponseData?>

    abstract suspend fun getAccessToken()

    abstract suspend fun getUserProfileInfo()

    abstract suspend fun getUserRepositories()

    abstract suspend fun searchRepositories(q: String)

    abstract suspend fun searchUsers(q: String)

}
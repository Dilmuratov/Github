package com.example.github.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import com.example.github.domain.usecases.getaccesstokenusecase.GetAccessTokenUseCase
import com.example.github.domain.usecases.getprofileinfousecase.GetProfileInfoUseCase
import com.example.github.domain.usecases.getuserrepositoriesusecase.GetUserRepositoriesUseCase
import com.example.github.domain.usecases.searchrepositoriesusecase.SearchRepositoriesUseCase
import com.example.github.domain.usecases.searchusersusecase.SearchUsersUseCase

class MainViewModelImpl(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase,
    private val searchUsersUseCase: SearchUsersUseCase
) : MainViewModel() {

    private val _getAccessTokenLiveData = MutableLiveData<String?>()
    override val getAccessTokenLiveData: LiveData<String?>
        get() = _getAccessTokenLiveData

    private val _getUserRepositoriesLiveData = MutableLiveData<List<RepositoryData>?>()
    override val getUserRepositoriesLiveData: LiveData<List<RepositoryData>?>
        get() = _getUserRepositoriesLiveData

    private val _searchRepositoriesLiveData = MutableLiveData<SearchResponseData<RepositoryItem>?>()
    override val searchRepositoriesLiveData: LiveData<SearchResponseData<RepositoryItem>?>
        get() = _searchRepositoriesLiveData

    private val _searchUsersLiveData = MutableLiveData<SearchResponseData<UserItem>?>()
    override val searchUsersLiveData: LiveData<SearchResponseData<UserItem>?>
        get() = _searchUsersLiveData

    private val _messageLiveData = MutableLiveData<String>()
    override val messageLiveData: LiveData<String>
        get() = _messageLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    override val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData

    private val _getUserProfileLiveData = MutableLiveData<UserProfileInfoResponseData?>()
    override val getUserProfileLiveData: LiveData<UserProfileInfoResponseData?>
        get() = _getUserProfileLiveData

    override suspend fun getAccessToken() {
        getAccessTokenUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> {
                    _getAccessTokenLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override suspend fun getUserProfileInfo() {
        getProfileInfoUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> {
                    _getUserProfileLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override suspend fun getUserRepositories() {
        getUserRepositoriesUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> {
                    _getUserRepositoriesLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override suspend fun searchRepositories(q: String) {
        searchRepositoriesUseCase.execute(q).collect {
            when (it) {
                is ResultData.Success -> {
                    _searchRepositoriesLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override suspend fun searchUsers(q: String) {
        searchUsersUseCase.execute(q).collect {
            when (it) {
                is ResultData.Success -> {
                    _searchUsersLiveData.value = it.data
                }
                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }
                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }
}
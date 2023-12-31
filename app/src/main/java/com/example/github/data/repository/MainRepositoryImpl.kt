package com.example.github.data.repository

import android.util.Log
import com.example.github.data.local.Constants
import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserfollowers.FollowerItem
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import com.example.github.data.network.interceptors.AccessKeyInterceptor
import com.example.github.data.network.retrofit.Api
import com.example.github.domain.network.MainRepository
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepositoryImpl : MainRepository {

    private var api: Api

    override suspend fun getAccessToken(): Flow<ResultData<String>> = flow {
        val clientId = Constants.client_id
        val clientSecret = Constants.client_secret
        val code = getStringFromPref("code").toString()
        val response = api.getAccessToken(clientId, clientSecret, code)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.accessToken))
        } else emit(ResultData.Message(response.message()))
    }.catch {
        it.printStackTrace()
    }

    override suspend fun getUserProfileInfo(): Flow<ResultData<UserProfileInfoResponseData>> =
        flow {
            val response = api.getUserProfileInfo()
            if (response.isSuccessful) {
                emit(ResultData.Success(response.body()!!))
            } else {
                emit(ResultData.Message(response.message()))
                Log.d(logTag, "getUserProfileInfo message: ${response.message()}")
            }
        }.catch {
            it.printStackTrace()
        }

    override suspend fun getUserRepositories(): Flow<ResultData<List<RepositoryData>>> = flow {
        val response = api.getUserRepositories()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
            Log.d(logTag, "getUserRepositories message: ${response.message()}")
        }
    }.catch { it.printStackTrace() }

    override suspend fun searchRepositories(q: String): Flow<ResultData<SearchResponseData<RepositoryItem>>> =
        flow {
            val response = api.searchRepositories(q)
            if (response.isSuccessful) {
                emit(ResultData.Success(response.body()!!))
            } else {
                emit(ResultData.Message(response.message()))
                Log.d(logTag, "searchRepositories message: ${response.message()}")
            }
        }.catch { it.printStackTrace() }

    override suspend fun searchUsers(q: String): Flow<ResultData<SearchResponseData<UserItem>>> =
        flow {
            val response = api.searchUsers(q)
            if (response.isSuccessful) {
                emit(ResultData.Success(response.body()!!))
            } else {
                emit(ResultData.Message(response.message()))
                Log.d(logTag, "searchUsers message: ${response.message()}")
            }
        }.catch { it.printStackTrace() }

    override suspend fun getUserFollowers(login: String, type: String): Flow<ResultData<List<FollowerItem>>> = flow {
        val response = api.getUserFollowers(login, type)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
            Log.d(logTag, "getUserFollowers message: ${response.message()}")
        }
    }.catch { it.printStackTrace() }

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AccessKeyInterceptor()).build()

        val retrofit = Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

        api = retrofit.create(Api::class.java)

    }

}
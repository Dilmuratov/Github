package com.example.github.data.repository

import android.util.Log
import com.example.github.data.local.Constants
import com.example.github.data.models.ResultData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import com.example.github.data.network.interceptors.AccessKeyInterceptor
import com.example.github.data.network.retrofit.Api
import com.example.github.domain.MainRepository
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepositoryImpl() : MainRepository {

    private var api: Api

    override suspend fun getAccessToken(): Flow<ResultData<String>> = flow {
        val clientId = Constants.client_id
        val clientSecret = Constants.client_secret
        val code = getStringFromPref("code").toString()
        val response = api.getAccessToken(clientId, clientSecret, code)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!.accessToken))
        } else
            emit(ResultData.Message(response.message()))
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

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AccessKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(Api::class.java)

    }

}
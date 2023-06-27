package com.example.github.data.network.retrofit

import com.example.github.data.models.getaccesstoken.GetAccessTokenResponseData
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.userprofileinfo.UserProfileInfoResponseData
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Response<GetAccessTokenResponseData>


    @GET("/user")
    suspend fun getUserProfileInfo(): Response<UserProfileInfoResponseData>

    @GET("/user/repos")
    suspend fun getUserRepositories(): Response<List<RepositoryData>>

}
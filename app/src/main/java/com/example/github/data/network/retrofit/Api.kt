package com.example.github.data.network.retrofit

import com.example.github.data.models.getaccesstoken.GetAccessTokenResponseData
import com.example.github.data.models.getuserfollowers.FollowerItem
import com.example.github.data.models.getuserrepositories.RepositoryData
import com.example.github.data.models.searchrepositories.RepositoryItem
import com.example.github.data.models.searchrepositories.SearchResponseData
import com.example.github.data.models.searchusers.UserItem
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


    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") q: String
    ): Response<SearchResponseData<RepositoryItem>>


    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") q: String
    ): Response<SearchResponseData<UserItem>>


    @GET("/users/{login}/{type}")
    suspend fun getUserFollowers(
        @Path("login") login: String,
        @Path("type") type: String
    ): Response<List<FollowerItem>>

}
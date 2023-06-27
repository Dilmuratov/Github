package com.example.github.data.models.getaccesstoken

import com.google.gson.annotations.SerializedName

data class GetAccessTokenResponseData(
    @SerializedName("access_token")
    val accessToken: String,
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)
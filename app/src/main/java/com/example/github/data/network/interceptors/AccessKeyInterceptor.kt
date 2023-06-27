package com.example.github.data.network.interceptors

import android.util.Log
import com.example.github.utils.getStringFromPref
import com.example.github.utils.logTag
import okhttp3.Interceptor

class AccessKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${getStringFromPref("access_key")}"
            )
            .build()
    ).apply {
        Log.d(logTag, "Access_key: ${getStringFromPref("access_key")}")
    }
}
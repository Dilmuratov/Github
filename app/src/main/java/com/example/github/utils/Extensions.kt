package com.example.github.utils

import android.util.Log
import com.example.github.data.local.LocalStorage

const val logTag = "TTTT"

fun showLoading() {
    Log.d("TTTT", "Showing Loading")
}

fun getStringFromPref(string: String): String? {
    val value = LocalStorage.pref.getString(string, string)
    Log.d(logTag, "$string value is $value")
    return value
}

fun putStringToPref(keyWord: String, value: String) =
    LocalStorage.pref.edit().putString(keyWord, value).apply()
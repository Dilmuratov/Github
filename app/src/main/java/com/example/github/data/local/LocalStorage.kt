package com.example.github.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.github.app.App

class LocalStorage {

    companion object {
        val pref: SharedPreferences =
            App.context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    }
}
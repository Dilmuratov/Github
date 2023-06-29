package com.example.github.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.github.data.local.LocalStorage
import com.squareup.picasso.Picasso

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

fun LinearLayout.hide() {
    this.visibility = View.GONE
}

fun LinearLayout.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.set(uri: String) {
    Picasso.get().load(uri).transform(CircleTransformation()).into(this)
}
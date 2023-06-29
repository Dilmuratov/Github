package com.example.github.data.models.searchrepositories

data class SearchResponseData<T>(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<T>
)

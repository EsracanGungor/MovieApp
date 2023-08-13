package com.esracangungor.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    @SerializedName("results")
    val results: List<PopularMovieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
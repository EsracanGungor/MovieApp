package com.esracangungor.movieapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TMDB_API_KEY = "910cb471f3326152066529eef1b406b2"
const val TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original/"
const val TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/"
const val TMDB_URL = "https://www.themoviedb.org/"


object MovieApiClient {
    val api: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
}
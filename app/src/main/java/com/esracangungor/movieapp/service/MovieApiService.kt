package com.esracangungor.movieapp.service

import com.esracangungor.movieapp.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<Movies>
}
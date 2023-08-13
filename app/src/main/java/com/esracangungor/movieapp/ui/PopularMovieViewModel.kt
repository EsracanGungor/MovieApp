package com.esracangungor.movieapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esracangungor.movieapp.model.Movies
import com.esracangungor.movieapp.model.PopularMovieItem
import com.esracangungor.movieapp.service.MovieApiClient
import com.esracangungor.movieapp.service.TMDB_API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<PopularMovieItem>>()
    fun getPopularMovies() {
        MovieApiClient.api.getPopularMovies(TMDB_API_KEY).enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body() != null) {
                    movieLiveData.value = response.body()!!.results
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }

    fun observeMovieLiveData(): LiveData<List<PopularMovieItem>> {
        return movieLiveData
    }
}
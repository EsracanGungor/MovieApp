package com.esracangungor.movieapp.ui

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.esracangungor.movieapp.R
import com.esracangungor.movieapp.databinding.ActivityMainBinding
import com.esracangungor.movieapp.model.PopularMovieItem
import com.esracangungor.movieapp.service.TMDB_URL


class MainActivity : AppCompatActivity(), PopularMovieListAdapter.MovieItemClickInterface {
    private lateinit var viewModel: PopularMovieViewModel
    private lateinit var binding: ActivityMainBinding
    private var customTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient? = null
    var customTabsSession: CustomTabsSession? = null
    private lateinit var movieAdapter: PopularMovieListAdapter
    private val chromePackageName = "com.android.chrome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[PopularMovieViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this) { movieList ->
            movieAdapter.updateMovieList(movieList)

        }

    }

    private fun prepareRecyclerView() {
        movieAdapter = PopularMovieListAdapter(this)
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onMovieItemClick(movieItem: PopularMovieItem) {
        customTabLinking()
    }

    private fun customTabLinking() {
        customTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                componentName: ComponentName,
                customTabsClient: CustomTabsClient
            ) { //pre-warning means to fast the surfing
                mClient = customTabsClient
                mClient?.warmup(0L)
                customTabsSession = mClient?.newSession(null)
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
                mClient = null
            }
        }

        CustomTabsClient.bindCustomTabsService(
            applicationContext,
            chromePackageName,
            customTabsServiceConnection as CustomTabsServiceConnection
        )
        val uri = Uri.parse(TMDB_URL)

        @ColorInt val colorPrimaryLight =
            ContextCompat.getColor(this@MainActivity, R.color.indigo)
        @ColorInt val colorPrimaryDark =
            ContextCompat.getColor(this@MainActivity, R.color.purple_200)

        val intentBuilder = CustomTabsIntent.Builder()
            // set the default color scheme
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(colorPrimaryLight)
                    .build()
            )
            // set the alternative dark color scheme
            .setColorSchemeParams(
                CustomTabsIntent.COLOR_SCHEME_DARK, CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(colorPrimaryDark)
                    .build()
            )

        // set the exit animations
        intentBuilder.setExitAnimations(
            applicationContext,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )

        //build custom tabs intent
        val customTabsIntent = intentBuilder.build()
        customTabsIntent.intent.setPackage(chromePackageName)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentBuilder.setShowTitle(true)
        intentBuilder.setUrlBarHidingEnabled(true)
        customTabsIntent.launchUrl(applicationContext, uri)
    }
}
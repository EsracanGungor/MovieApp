<h1 align="center">MovieApp</h1>

<p align="center">  
 MovieApp demonstrates  simple Android app with Chrome Custom Tabs, ViewModel, ViewBinding, Coil, Retrofit and Material Design based on MVVM architecture.</p>
 
## Medium
-For further explanation you can read medium article [click](https://github.com/EsracanGungor/MovieApp/releases/tag/1.0.0) here.

<table>
  <tr>
    <td>Movie List Page</td>
     <td>Chrome Custom Tabs Page</td>
  </tr>
  <tr>
    <td><img src="/previews/movieAppHomepage.jpg" ></td>
    <td><img src="/previews/chromeCustomTabsPage.jpg" ></td>
  </tr>
  </table>
<table>

## Functionality
The app's functionality includes:
1. Fetch a list of popular movies from tmdb api (https://developer.themoviedb.org/reference/intro/getting-started) and shows them in RecyclerView.. 
2. Click a movie and redirect tmdb homepage. 

## Tech Stack & Open-source Libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/), [Android appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat)
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) for observe Android lifecycles and handle UI states upon the lifecycle changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) for manage UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- [Android View Binding](https://developer.android.com/topic/libraries/view-binding)
- [Retrofit2](https://github.com/square/retrofit) for REST API communication.
- [Coil](https://github.com/coil-kt/coil) for loading images from network.
  
## Architecture
MovieApp is based on the clean architecture with MVVM(Model - View - View Model) design pattern.

## Download
Go to the [Releases](https://github.com/EsracanGungor/MovieApp/releases) to download the APK.

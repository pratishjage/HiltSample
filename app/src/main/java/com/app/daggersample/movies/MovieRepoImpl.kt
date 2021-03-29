package com.app.daggersample.movies

import android.annotation.SuppressLint
import android.util.Log
import com.app.daggersample.movies.model.MovieDataModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieRepoImpl @Inject constructor(private val service: MovieService) : MovieRepo {
  @SuppressLint("LongLogTag")
  override suspend fun getAllMovies(): List<MovieDataModel> {
    Log.d("MovieRepoImpl_serviceInstance", service.toString())
    Log.d("MovieRepoImpl_MovieRepoImplInstance", this.toString())
    return service.getAllMovies()
  }
}
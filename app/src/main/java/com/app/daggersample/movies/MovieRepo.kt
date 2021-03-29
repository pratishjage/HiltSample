package com.app.daggersample.movies

import com.app.daggersample.movies.model.MovieDataModel

interface MovieRepo {
  suspend fun getAllMovies(): List<MovieDataModel>
}
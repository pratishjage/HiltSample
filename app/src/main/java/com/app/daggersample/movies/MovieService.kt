package com.app.daggersample.movies

import com.app.daggersample.movies.model.MovieDataModel
import retrofit2.http.GET

interface MovieService {
  @GET("b/4Q4H56")
  suspend fun getAllMovies() :List<MovieDataModel>
}
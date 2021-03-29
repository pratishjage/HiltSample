package com.app.daggersample.movies

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MovieModule {

  @Provides
  @ViewModelScoped
  fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  // @Provides
  // @ViewModelScoped
  // fun provideMovieRepo(service: MovieService): MovieRepo {
  //   return MovieRepoImpl(service)
  // }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

  @Binds
  @ViewModelScoped
  abstract fun bindMovieRepo(impl: MovieRepoImpl): MovieRepo
}
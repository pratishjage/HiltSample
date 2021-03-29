package com.app.daggersample.movies

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.daggersample.base.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.liveData
import com.app.daggersample.base.extensions.hide
import com.app.daggersample.base.extensions.show
import com.app.daggersample.movies.model.MovieDataModel
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MovieRepo) : BaseVM() {
  val movies = MutableLiveData<List<MovieDataModel>>()

  @SuppressLint("LongLogTag")
  fun getMovies(): LiveData<List<MovieDataModel>> {
    viewModelScope.launch(Dispatchers.Default) {
      Log.d("MovieViewModel_repoInstance", repo.toString())
      Log.d("MovieViewModel_vmInstance", this.toString())
      try {
        _loading.show()
        movies.postValue(repo.getAllMovies())
        _loading.hide()
      } catch (e: Exception) {
        Log.d("MovieViewModel_errorrrr", e.localizedMessage)
        handleError(e)
        e.printStackTrace()
      }
    }
    return movies;
  }
}
package com.app.daggersample.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.daggersample.R
import com.app.daggersample.base.ui.BaseFragment
import com.app.daggersample.base.ui.BaseVM
import com.app.daggersample.movies.MovieViewModel
import com.app.daggersample.movies.model.MovieDataModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_fragment.tv

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

  companion object {
    fun newInstance() = MovieFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.movie_fragment, container, false)
  }

  override val vm: MovieViewModel by viewModels()

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    observe(vm.getMovies(), ::updateUi)
  }

  private fun updateUi(list: List<MovieDataModel>) {
    tv.text = list.toString()
  }
}
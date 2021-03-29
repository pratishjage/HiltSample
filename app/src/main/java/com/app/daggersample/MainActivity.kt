package com.app.daggersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.app.daggersample.movies.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: MovieViewModel by viewModels()

  @Inject
  lateinit var sampleText: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    // findViewById<TextView>(R.id.tv).text = sampleText
    // findViewById<Button>(R.id.btn).setOnClickListener {
    //   startActivity(Intent(this, SampleActivity::class.java))
    // }
    // viewModel.getMovies()
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("MainActivity","onDestroy Called")
  }
}
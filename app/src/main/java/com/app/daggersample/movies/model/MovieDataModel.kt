package com.app.daggersample.movies.model

import com.google.gson.annotations.SerializedName

data class MovieDataModel(
  @SerializedName("id") val id: String,
  @SerializedName("title") val title: String,
  @SerializedName("posterurl") val posterUrl: String,
  @SerializedName("storyline") val storyLine: String
)

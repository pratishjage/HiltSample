package com.app.daggersample.base.extensions

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.hide() = this.postValue(false)

fun MutableLiveData<Boolean>.show() = this.postValue(true)
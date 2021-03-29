package com.app.daggersample.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.daggersample.base.extensions.hide

abstract class BaseVM : ViewModel() {

  protected val _loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
  val loading: LiveData<Boolean> by lazy { _loading }

  protected val _error: MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }
  val error: LiveData<Throwable> by lazy { _error }


  protected fun handleError(error: Throwable) {
    _loading.hide()
    _error.postValue(error)
  }

  override fun onCleared() {
    super.onCleared()
  }

  fun errorHandled() {
    _error.value = null
  }
}
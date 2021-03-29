package com.app.daggersample.base.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.daggersample.R
import com.app.daggersample.base.utils.CriticalException
import com.app.daggersample.base.utils.ShowDialogException
import com.app.daggersample.base.utils.ShowSnakBarException
import com.app.daggersample.base.utils.UnMappedNetworkException
import com.google.android.material.snackbar.Snackbar
import java.net.ConnectException
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {

  abstract val vm: BaseVM

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    listenForLoadingState()

    listenForErrorState()
  }

  @CallSuper
  private fun listenForErrorState() {
    observe(vm.error, { error ->
      vm.errorHandled()
      when (error) {
        is ShowSnakBarException -> showSnakBar(error.message)
        is ShowDialogException -> showSnakBar(error.message)
        is CriticalException -> showSnakBar(error.message)
        is ConnectException -> handleCriticalException(error.message)
        is UnknownHostException -> handleNetworkException(error.message)
        is UnMappedNetworkException -> showSnakBar(error.localizedMessage)
        else -> handleNetworkException(error.message)
      }

    })
  }

  private fun handleNetworkException(message: String?) {
    findNavController().navigate(R.id.action_global_networkErrorFragment)
  }

  private fun handleCriticalException(message: String?) {
    handleNetworkException(message)
  }

  private fun showSnakBar(message: String?, length: Int = Snackbar.LENGTH_SHORT) {
    val reason = if (message.isNullOrEmpty()) {
      getString(R.string.something_went_wrong)
    } else {
      message
    }

    activity?.window?.decorView?.let {
      val snackbar = Snackbar.make(it.findViewById(android.R.id.content), reason, length)
      val view = snackbar.view

      activity?.let { act ->
        view.setBackgroundColor(ContextCompat.getColor(act, R.color.colorPrimary))
        snackbar.show()
      }

    }
  }

  @CallSuper
  private fun listenForLoadingState() {
    observe(vm.loading, { loading ->
      when (loading) {
        true -> showLoading()
        else -> hideLoading()
      }

    })
  }

  private fun hideLoading() {
    if (activity != null && activity is BaseActivity) {
      (activity as BaseActivity).hideProgressDialog()
    }
  }

  private fun showLoading() {
    if (activity != null && activity is BaseActivity) {
      (activity as BaseActivity).showProgressDialog()
    }
  }

  protected fun <T> observe(
    liveData: LiveData<T>,
    onChanged: (T) -> Unit,
    owner: LifecycleOwner = viewLifecycleOwner
  ) {
    if (view != null) {
      liveData.observe(owner, Observer { data ->
        if (data == null) return@Observer
        onChanged(data)
      })
    }
  }
}
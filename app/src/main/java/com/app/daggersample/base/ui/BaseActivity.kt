package com.app.daggersample.base.ui

import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.daggersample.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BaseActivity : AppCompatActivity() {
  private var alertDialog: AlertDialog? = null

  fun showProgressDialog() {
    if (alertDialog == null) {
      val builder = MaterialAlertDialogBuilder(this)
      val view = LayoutInflater.from(this).inflate(R.layout.loading_dialog_layout, null)
      builder.setView(view)
      alertDialog = builder.create()
      alertDialog!!.setCancelable(false)
      alertDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }
    if (alertDialog!!.isShowing) {
      alertDialog!!.dismiss()
    }
    val window = alertDialog!!.window
    if (!isFinishing && window != null && hasWindowFocus()) {
      alertDialog!!.show()
    }
  }

  fun hideProgressDialog() {
    alertDialog?.dismiss()
  }
}
package com.teo.currency.exchanger.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<
        VIEW : BaseContractView,
        PRESENTER : BaseContractPresenter<VIEW>> : AppCompatActivity() {
    protected val TAG: String = BaseActivity::class.java.simpleName

    @Inject
    lateinit var presenter: PRESENTER

    private var alertDialog: AlertDialog? = null

    abstract fun initInjects()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjects()
        presenter.view = this as VIEW

        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    protected open fun showMessageDialog(
        message: String?,
        onCloseListener: (() -> Unit)? = null
    ) {
        if (alertDialog != null) {
            alertDialog!!.setMessage(message)
        } else {
            alertDialog = AlertDialog.Builder(this)
                .setMessage(message)
                .setOnDismissListener {
                    onCloseListener?.invoke()
                }
                .setNegativeButton(android.R.string.ok) { dialog, _ ->
                    dialog.cancel()
                }
                .create()
        }
        if (alertDialog?.isShowing == false) {
            alertDialog?.show()
        }
    }
}
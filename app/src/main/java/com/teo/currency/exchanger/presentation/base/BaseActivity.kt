package com.teo.currency.exchanger.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import moxy.MvpAppCompatActivity

abstract class BaseActivity<VIEW : BaseView> : MvpAppCompatActivity(), BaseView {
    protected val TAG: String = BaseActivity::class.java.simpleName

    private var alertDialog: AlertDialog? = null

    abstract fun initInjects()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjects()
        super.onCreate(savedInstanceState)
    }

    public open fun showMessageDialog(
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
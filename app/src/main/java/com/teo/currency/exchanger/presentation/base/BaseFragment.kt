package com.teo.currency.exchanger.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {
    protected val TAG: String = BaseFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjects()
        super.onCreate(savedInstanceState)
    }

    abstract fun initInjects()

    protected open fun showMessageDialog(
        message: String?,
        onCloseListener: (() -> Unit)? = null
    ) {
        (activity as? BaseActivity<*>)?.showMessageDialog(message, onCloseListener)
    }
}
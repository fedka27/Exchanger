package com.teo.currency.exchanger.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<
        VIEW : BaseContractView,
        PRESENTER : BaseContractPresenter<VIEW>> : Fragment() {
    protected val TAG: String = BaseFragment::class.java.simpleName

    @Inject
    lateinit var presenter: PRESENTER

    private var alertDialog: AlertDialog? = null

    abstract fun initInjects()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjects()
        presenter.view = this as VIEW

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        (activity as? BaseActivity<*, *>)?.showMessageDialog(message, onCloseListener)
    }
}
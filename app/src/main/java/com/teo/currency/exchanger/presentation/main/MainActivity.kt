package com.teo.currency.exchanger.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.components.providers.main.MainComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseActivity

class MainActivity :
    BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    override fun initInjects() {
        MainComponentProvider.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
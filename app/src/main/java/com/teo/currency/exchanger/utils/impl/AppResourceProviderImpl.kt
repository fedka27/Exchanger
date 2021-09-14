package com.teo.currency.exchanger.utils.impl

import android.content.Context
import com.teo.currency.exchanger.utils.AppResourceProvider

class AppResourceProviderImpl(private val context: Context) : AppResourceProvider {
    override fun getString(res: Int): String {
        return context.getString(res)
    }
}
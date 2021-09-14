package com.teo.currency.exchanger.utils

import androidx.annotation.StringRes

interface AppResourceProvider {
    fun getString(@StringRes res: Int): String
}
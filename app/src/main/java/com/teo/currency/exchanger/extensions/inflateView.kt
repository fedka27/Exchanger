package com.teo.currency.exchanger.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

inline fun ViewGroup.inflateView(
    @LayoutRes layoutRes: Int,
    root: ViewGroup? = this,
    attachToRoot: Boolean = false
): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, root, attachToRoot)
}
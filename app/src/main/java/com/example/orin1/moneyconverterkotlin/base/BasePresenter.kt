package com.example.orin1.moneyconverterkotlin.base

import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {
    private var viewRef: WeakReference<V>? = null

    val view: V?
        get() = viewRef?.get()

    fun attachView(view: V) {
        viewRef = WeakReference(view)
        onAttach()
    }

    fun detachView() {
        onDetach()
        viewRef?.clear()
    }

    protected abstract fun onAttach()
    protected abstract fun onDetach()

}
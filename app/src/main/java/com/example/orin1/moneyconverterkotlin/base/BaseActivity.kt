package com.example.orin1.moneyconverterkotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity() {

    private var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachPresenter()
    }

    private fun attachPresenter() {

        var presenter: P? = lastCustomNonConfigurationInstance as? P
        if (presenter == null) {
            presenter = providePresenter()
        }
        presenter.attachView(this as V)
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    protected abstract fun providePresenter(): P

    protected fun getPresenter(): P {
        return presenter!!
    }
}
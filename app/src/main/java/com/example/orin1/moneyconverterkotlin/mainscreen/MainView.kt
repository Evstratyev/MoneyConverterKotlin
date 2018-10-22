package com.example.orin1.moneyconverterkotlin.mainscreen

import com.example.orin1.moneyconverterkotlin.base.BaseView
import com.example.orin1.moneyconverterkotlin.model.Currency

interface MainView : BaseView {
    abstract fun showLoadResult(result: List<Currency>)
    abstract fun initAdapterData(itemNames: List<String>)
    abstract fun showConvertResult(convertValue: Double)
    abstract fun updateRecyclerViewInfo(currencies: List<Currency>)
}
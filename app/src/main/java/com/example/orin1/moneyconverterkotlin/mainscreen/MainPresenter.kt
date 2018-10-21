package com.example.orin1.moneyconverterkotlin.mainscreen

import android.util.Log
import com.example.orin1.moneyconverterkotlin.base.BasePresenter
import com.example.orin1.moneyconverterkotlin.model.Currency
import com.example.orin1.moneyconverterkotlin.model.CurrencyService
import com.example.orin1.moneyconverterkotlin.model.callbacks.DataLoadInstance

class MainPresenter : BasePresenter<MainView> {

    private var service: CurrencyService? = null

    constructor() {
        this.service = CurrencyService.instance
    }

    override fun onAttach() {
        service!!.loadData(object : DataLoadInstance {

            override fun dataAllreadyLoaded(currencies: List<Currency>, currencyNames: List<String>) {
                this@MainPresenter.view!!.initAdapterData(currencyNames)
                this@MainPresenter.view!!.showLoadResult(currencies)
            }

            override fun dataIsLoad(currencies: List<Currency>, currencyNames: List<String>) {
                Log.i("loaddata", "callback_+")
                this@MainPresenter.view!!.initAdapterData(currencyNames)
                this@MainPresenter.view!!.showLoadResult(currencies)
            }
        })


    }

    override fun onDetach() {    }

    fun convertValue(mainSpinnerCurrencyName: String,
                     secondSpinnerCurrencyName: String,
                     convertValue: Double) {
        this@MainPresenter.view!!.showConvertResult(service!!.convertResult(
                mainSpinnerCurrencyName,
                secondSpinnerCurrencyName,
                convertValue))
    }

    fun getCurrencyNames(): List<String> {
        return service!!.currencyNameList
    }

    fun updateRecyclerViewInfo(mainSpinnerCurrencyName: String) {
        this@MainPresenter.view!!.updateRecyclerViewInfo(
                service!!.convertMainCurrencyRatesList(mainSpinnerCurrencyName))
    }
}
package com.example.orin1.moneyconverterkotlin.model.callbacks

import com.example.orin1.moneyconverterkotlin.model.Currency

interface DataLoadInstance {
    fun dataAllreadyLoaded(currencies: List<Currency>, currencyNames: List<String>)
    fun dataIsLoad(currencies: List<Currency>, currencyNames: List<String>)
}
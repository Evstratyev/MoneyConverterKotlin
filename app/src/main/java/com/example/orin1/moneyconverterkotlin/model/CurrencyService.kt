package com.example.orin1.moneyconverterkotlin.model

import android.util.Log
import com.example.orin1.moneyconverterkotlin.model.callbacks.DataLoadInstance
import com.example.orin1.moneyconverterkotlin.model.api.ApiController
import com.example.orin1.moneyconverterkotlin.model.api.ApiObject
import com.example.orin1.moneyconverterkotlin.model.api.ServerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class CurrencyService private constructor() {

    init {
    }

    private object Holder {
        val INSTANCE = CurrencyService()
    }

    companion object {
        val instance: CurrencyService by lazy { Holder.INSTANCE }
    }
    /*
    реализовать синглтон
     */

    private val currencyRatesList = ArrayList<Currency>()
    var currencyNameList = ArrayList<String>()
    /*
    как сделать геттер если поле должно быть private ?
    необходимо вызывать в mainpresenter
     */

    private var dataLoaded: Boolean = false
    private var loadThreadStart: Boolean = false
    private val serverApi: ServerApi? = ApiController.getApi()

    fun loadData(dataLoadInstance: DataLoadInstance) {

        Log.i("loaddata", "thread start")

        if (!dataLoaded) {
            if (!loadThreadStart) {

                Thread(Runnable {
                    loadThreadStart = true

                    try {

                        Thread.sleep(5_000)

                        serverApi?.request()?.enqueue(object : Callback<ApiObject> {

                            override fun onResponse(call: Call<ApiObject>, response: Response<ApiObject>) {
                                val apiObject: ApiObject? = response.body()

                                var setKeys: Set<String>? = apiObject?.rates?.getRatesMap()?.keys
                                if (setKeys != null) {
                                    for (keyRate in setKeys) {
                                        currencyNameList.add(keyRate)
                                        currencyRatesList.add(
                                                com.
                                                example.
                                                orin1.
                                                moneyconverterkotlin.
                                                model.
                                                Currency(keyRate, apiObject?.rates?.getRatesMap()?.get(keyRate)!!)
                                                )
                                    }
                                }
                                currencyNameList.add(apiObject?.base.toString().toUpperCase())
                                currencyRatesList.add(com.
                                        example.
                                        orin1.
                                        moneyconverterkotlin.
                                        model.
                                        Currency(apiObject?.base.toString().toUpperCase(), 1.0))
                                currencyRatesList.sort()
                                currencyNameList.sort()
                                dataLoadInstance.dataIsLoad(currencyRatesList, currencyNameList)
                                dataLoaded = true
                            }

                            override fun onFailure(call: Call<ApiObject>, t: Throwable) {
                                TODO()
                            }
                        })

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }).start()
            }
        } else {
            dataLoadInstance.dataAllreadyLoaded(currencyRatesList, currencyNameList)
        }
    }

    fun convertResult(mainCurrency: String, convertCurrency: String, inputValue: Double): Double {
        val convertResult: Double
        var mainCurrencyRate = 0.0
        var convertCurrencyRate = 0.0

        for (currency in currencyRatesList) {
            if (mainCurrency == currency.name) {
                mainCurrencyRate = currency.rate
            } else if (convertCurrency == currency.name) {
                convertCurrencyRate = currency.rate
            }
        }

        convertResult = inputValue * (convertCurrencyRate / mainCurrencyRate)

        return convertResult
    }

    fun convertMainCurrencyRatesList(mainCurrencyName: String): List<Currency> {
        val temp = ArrayList(currencyRatesList)
        var delCurrency: Currency? = null

        var convertCoefficient = 1.0
        for (currency in temp) {
            if (currency.name.equals(mainCurrencyName)) {
                convertCoefficient = currency.rate
                delCurrency = currency
                break
            }
        }
        for (currency in temp) {

            var d = 0.0
            d = currency.rate / convertCoefficient
            currency.rate = d
        }

        temp.remove(delCurrency)

        return temp
    }
}
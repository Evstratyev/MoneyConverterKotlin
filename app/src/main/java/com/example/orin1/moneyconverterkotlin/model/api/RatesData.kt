package com.example.orin1.moneyconverterkotlin.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RatesData {
    @SerializedName("BGN")
    @Expose
    var bgn: Double = 0.toDouble()
    @SerializedName("CAD")
    @Expose
    var cad: Double = 0.toDouble()
    @SerializedName("BRL")
    @Expose
    var brl: Double = 0.toDouble()
    @SerializedName("HUF")
    @Expose
    var huf: Double = 0.toDouble()
    @SerializedName("DKK")
    @Expose
    var dkk: Double = 0.toDouble()
    @SerializedName("JPY")
    @Expose
    var jpy: Double = 0.toDouble()
    @SerializedName("ILS")
    @Expose
    var ils: Double = 0.toDouble()
    @SerializedName("TRY")
    @Expose
    var `try`: Double = 0.toDouble()
    @SerializedName("RON")
    @Expose
    var ron: Double = 0.toDouble()
    @SerializedName("GBP")
    @Expose
    var gbp: Double = 0.toDouble()
    @SerializedName("PHP")
    @Expose
    var php: Double = 0.toDouble()
    @SerializedName("HRK")
    @Expose
    var hrk: Double = 0.toDouble()
    @SerializedName("NOK")
    @Expose
    var nok: Double = 0.toDouble()
    @SerializedName("USD")
    @Expose
    var usd: Double = 0.toDouble()
    @SerializedName("MXN")
    @Expose
    var mxn: Double = 0.toDouble()
    @SerializedName("AUD")
    @Expose
    var aud: Double = 0.toDouble()
    @SerializedName("IDR")
    @Expose
    var idr: Double = 0.toDouble()
    @SerializedName("KRW")
    @Expose
    var krw: Double = 0.toDouble()
    @SerializedName("HKD")
    @Expose
    var hkd: Double = 0.toDouble()
    @SerializedName("ZAR")
    @Expose
    var zar: Double = 0.toDouble()
    @SerializedName("ISK")
    @Expose
    var isk: Double = 0.toDouble()
    @SerializedName("CZK")
    @Expose
    var czk: Double = 0.toDouble()
    @SerializedName("THB")
    @Expose
    var thb: Double = 0.toDouble()
    @SerializedName("MYR")
    @Expose
    var myr: Double = 0.toDouble()
    @SerializedName("NZD")
    @Expose
    var nzd: Double = 0.toDouble()
    @SerializedName("PLN")
    @Expose
    var pln: Double = 0.toDouble()
    @SerializedName("SEK")
    @Expose
    var sek: Double = 0.toDouble()
    @SerializedName("RUB")
    @Expose
    var rub: Double = 0.toDouble()
    @SerializedName("CNY")
    @Expose
    var cny: Double = 0.toDouble()
    @SerializedName("SGD")
    @Expose
    var sgd: Double = 0.toDouble()
    @SerializedName("CHF")
    @Expose
    var chf: Double = 0.toDouble()
    @SerializedName("INR")
    @Expose
    var inr: Double = 0.toDouble()


    fun getRatesMap(): Map<String, Double> {
        val map = HashMap<String, Double>()
        map["BGN"] = bgn
        map["CAD"] = cad
        map["BRL"] = brl
        map["HUF"] = huf
        map["DKK"] = dkk
        map["JPY"] = jpy
        map["ILS"] = ils
        map["TRY"] = `try`
        map["RON"] = ron
        map["GBP"] = gbp
        map["PHP"] = php
        map["HRK"] = hrk
        map["NOK"] = nok
        map["USD"] = usd
        map["MXN"] = mxn
        map["AUD"] = aud
        map["IDR"] = idr
        map["KRW"] = krw
        map["HKD"] = hkd
        map["ZAR"] = zar
        map["ISK"] = isk
        map["CZK"] = czk
        map["THB"] = thb
        map["MYR"] = myr
        map["NZD"] = nzd
        map["PLN"] = pln
        map["SEK"] = sek
        map["RUB"] = rub
        map["CNY"] = cny
        map["SGD"] = sgd
        map["CHF"] = chf
        map["INR"] = inr
        return map
    }
}
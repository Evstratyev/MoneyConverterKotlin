package com.example.orin1.moneyconverterkotlin.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiObject {

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("rates")
    @Expose
    var rates: RatesData? = null

    @SerializedName("base")
    @Expose
    var base: String? = null

}
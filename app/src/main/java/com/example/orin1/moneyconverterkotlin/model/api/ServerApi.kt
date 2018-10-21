package com.example.orin1.moneyconverterkotlin.model.api

import retrofit2.Call
import retrofit2.http.GET

interface ServerApi {
    @GET("latest")
    fun request(): Call<ApiObject>
}
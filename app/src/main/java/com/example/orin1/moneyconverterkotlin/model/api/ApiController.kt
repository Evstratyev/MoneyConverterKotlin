package com.example.orin1.moneyconverterkotlin.model.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiController {

    companion object {
        fun getApi() :ServerApi {
            var gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

            var retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://api.exchangeratesapi.io/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            var serverApi: ServerApi = retrofit.create(ServerApi::class.java)

            return serverApi
        }
    }

}
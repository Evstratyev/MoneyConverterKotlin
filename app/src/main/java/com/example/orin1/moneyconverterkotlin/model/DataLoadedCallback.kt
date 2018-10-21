package com.example.orin1.moneyconverterkotlin.model

interface DataLoadedCallback <O,S> {
    abstract fun onDataloadedCallback(o: O, s: S)
}
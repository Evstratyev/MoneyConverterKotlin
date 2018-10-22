package com.example.orin1.moneyconverterkotlin.model.callbacks

interface DataLoadedCallback <O,S> {
    abstract fun onDataloadedCallback(o: O, s: S)
}
package com.example.orin1.moneyconverterkotlin.model

class Currency(var name: String, var rate: Double) : Comparable<Currency> {

    /*
    как сделать модификатор в конструкторе private
    и при этом правильно указать геттер и сеттер
    чтоб использовать его в currencyservice ?
     */

    override fun compareTo(o: Currency): Int {
        return name.compareTo(o.name)
    }

    

}
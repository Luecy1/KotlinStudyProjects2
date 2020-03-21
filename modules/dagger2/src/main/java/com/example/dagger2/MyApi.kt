package com.example.dagger2

// APIを用いて何かしらの結果を返す
interface MyApi {

    fun getResult(): String
}

class MyApiImpl : MyApi {

    override fun getResult(): String {
        return "Result!!!"
    }
}
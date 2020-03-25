package com.example.dagger_subcomponents

interface Api {
    fun getResult(): String
}

class ApiImpl : Api {
    override fun getResult(): String {
        return "API"
    }
}

interface SubApi {
    fun getResult(): String
}

class SubApiImpl : SubApi {
    override fun getResult(): String {
        return "API"
    }
}
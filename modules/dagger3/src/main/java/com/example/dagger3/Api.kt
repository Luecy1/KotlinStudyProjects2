package com.example.dagger3

import javax.inject.Inject

interface Api {
    fun getResult(): String
}

class ApiImpl : Api {
    override fun getResult(): String {
        return "API"
    }
}
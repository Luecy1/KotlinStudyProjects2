package com.example.dagger.di

import javax.inject.Inject

interface Api {
    fun getResult():String
}

class ApiImpl @Inject constructor() : Api {

    override fun getResult(): String {
        return "Result!!!"
    }

}


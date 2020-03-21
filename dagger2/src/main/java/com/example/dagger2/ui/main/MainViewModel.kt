package com.example.dagger2.ui.main

import androidx.lifecycle.ViewModel
import com.example.dagger2.MyApi
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val api: MyApi
) : ViewModel() {

    fun useApi() {
        Timber.d(api.getResult())
    }
}

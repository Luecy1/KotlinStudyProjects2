package com.example.dagger2.ui.lancher

import androidx.lifecycle.ViewModel
import com.example.dagger2.MyApi
import javax.inject.Inject

class LancherViewModel @Inject constructor(
    private val api: MyApi
) : ViewModel() {
}

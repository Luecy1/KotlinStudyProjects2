package com.example.kotlinstudyprojects.cancel

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CancelViewModel : ViewModel() {

    val text = MutableLiveData<String>("no count")

    var count = 0

    fun longTimeProcess() {

        val launch = viewModelScope.launch {
            count++
            Timber.d("count:$count")
            delay(5000) // 長い処理
            text.postValue("count:$count")
            Timber.d("count:$count")
        }

        launch.cancel()

    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared!!!")
    }
}

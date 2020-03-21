package com.example.dagger.di

import timber.log.Timber
import javax.inject.Inject

class Hoge @Inject constructor() {

    init {
        Timber.d("Hoge init!!!")
    }

    fun hogeMethod(): String {
        return "Hoge"
    }
}

// 外部ライブラリでconstructorに@injectを指定できないと仮定
class Fuga
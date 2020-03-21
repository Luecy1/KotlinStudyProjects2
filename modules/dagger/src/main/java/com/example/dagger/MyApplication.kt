package com.example.dagger

import android.app.Application
import com.example.dagger.di.DaggerAppComponent
import com.example.dagger.di.Hoge
import timber.log.Timber
import javax.inject.Inject

class MyApplication : Application() {

    @Inject
    lateinit var hoge: Hoge

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val appComponent = DaggerAppComponent.factory().create(this)

        val hogee = appComponent.hoge()//この地点で初期化
        Timber.d("hoge　インスタンスを初期化済み")
        Timber.d(hogee.hogeMethod())

        val hogeP = appComponent.hogeProvider()
        Timber.d("Provider<Hoge>　インスタンスを取得 Hogeはまだ未初期化")
        Timber.d("1:%s", hogeP.get().hogeMethod())
        Timber.d("2:%s", hogeP.get().hogeMethod())
        Timber.d("3:%s", hogeP.get().hogeMethod())

        val hogeLazy = appComponent.hogeLazy()
        Timber.d("1:%s", hogeLazy.get())
        Timber.d("2:%s", hogeLazy.get())
        Timber.d("3:%s", hogeLazy.get())

        Timber.d("メンバ変数にinject")
        // これでhogeメンバが使える
        appComponent.inject(this)
        Timber.d(hoge.hogeMethod())

        val api = appComponent.api()
        Timber.d("API = %s", api.getResult())


        val fruitSet = appComponent.fruit()
        Timber.d(fruitSet.toString())
    }
}
package com.example.dagger.di

import android.app.Application
import com.example.dagger.MyApplication
import com.example.dagger.di.multi.Fruit
import com.example.dagger.di.multi.FruitModule
import dagger.BindsInstance
import dagger.Component
import dagger.Lazy
import javax.inject.Provider

@Component(modules = [NetworkModule2::class, FruitModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun hoge(): Hoge

    fun hogeProvider(): Provider<Hoge>

    fun hogeLazy(): Lazy<Hoge>

    fun inject(application: MyApplication)

    fun api(): Api

    fun fruit(): Set<Fruit>
}
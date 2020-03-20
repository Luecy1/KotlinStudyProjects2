package com.example.dagger.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class AppModule {

    @Provides
    fun provideFuga(hoge: Hoge): Fuga {
        Timber.d(hoge.hogeMethod())
        return Fuga()
    }
}

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    fun provideHoge(): Hoge {
        return Hoge()
    }


}

@Module
interface NetworkModule2 {
    @Binds
    fun provideApi(impl: ApiImpl): Api
}
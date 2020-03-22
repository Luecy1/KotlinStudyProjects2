package com.example.dagger3.di

import com.example.dagger3.Api
import com.example.dagger3.ApiImpl
import com.example.dagger3.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainActivityModule::class,
        ApiModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)
}

//@ActivityScope // Scope が必要な場合
//@Subcomponent(modules = [...])
//interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
//    @Subcomponent.Factory
//    interface Factory : AndroidInjector.Factory<MainActivity> }

@Module
abstract class MainActivityModule {

    abstract fun contributeMainActivity(): MainActivity

}

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Api {
        return ApiImpl()
    }
}
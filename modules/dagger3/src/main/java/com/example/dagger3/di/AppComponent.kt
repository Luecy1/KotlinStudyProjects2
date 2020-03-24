package com.example.dagger3.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.dagger3.Api
import com.example.dagger3.ApiImpl
import com.example.dagger3.MyApplication
import com.example.dagger3.ui.main.MainFragment
import com.example.dagger3.ui.main.MainViewModel
import dagger.*
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ViewModelBuilder::class,
        MainViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Api {
        return ApiImpl()
    }
}

@Module
abstract class MainViewModelModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun mainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

}
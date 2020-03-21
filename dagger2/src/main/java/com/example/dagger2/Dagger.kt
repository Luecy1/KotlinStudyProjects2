package com.example.dagger2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagger2.ui.lancher.LancherViewModel
import com.example.dagger2.ui.main.MainViewModel
import dagger.*
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Component(modules = [AppModule::class, BindModule::class])
interface AppComponent {
    fun viewModelFactory(): ViewModelProvider.Factory
}

@Module
object AppModule {

    @Provides
    fun provideMyApi(): MyApi {
        return MyApiImpl()
    }
}

@Module
interface BindModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewmodel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LancherViewModel::class)
    fun bindLancherViewModel(viewmodel: LancherViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val key: KClass<out ViewModel>)
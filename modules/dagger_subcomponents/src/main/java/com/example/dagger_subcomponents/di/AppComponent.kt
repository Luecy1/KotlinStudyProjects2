package com.example.dagger_subcomponents.di

import com.example.dagger_subcomponents.Api
import com.example.dagger_subcomponents.ApiImpl
import com.example.dagger_subcomponents.SubApi
import com.example.dagger_subcomponents.SubApiImpl
import dagger.*
import javax.inject.Singleton

// ComponentはDaggerライブラリによって中身が作成されるため、interfaceかabstract
@Component(modules = [AppModule::class, SubComponentModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun subComponentFactory(): SubComponent.Factory
}

// Providesはインスタンス化の手段をDaggerに提供する
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return ApiImpl()
    }
}

@Subcomponent(modules = [SubModule::class])
interface SubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            module: SubModule
        ): SubComponent
    }
}

@Module
class SubModule {

    @Provides
    fun provideSubApi(): SubApi {
        return SubApiImpl()
    }
}

@Module(subcomponents = [SubComponent::class])
object SubComponentModule
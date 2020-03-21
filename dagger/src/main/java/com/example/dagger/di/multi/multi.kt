package com.example.dagger.di.multi

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

data class Fruit(val name: String)

@Module
class FruitModule {

    @Provides
    @IntoSet
    fun provideApple(): Fruit {
        return Fruit("apple")
    }

    @Provides
    @IntoSet
    fun provideBanana(): Fruit {
        return Fruit("banana")
    }
}
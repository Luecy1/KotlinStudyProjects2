package com.example.android_testing.di

import androidx.lifecycle.ViewModel
import com.example.android_testing.blank.BlankFragment
import com.example.android_testing.blank.BlankViewModel
import com.example.android_testing.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideMainFragment(): BlankFragment

    @Binds
    @IntoMap
    @ViewModelKey(BlankViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: BlankViewModel): ViewModel
}
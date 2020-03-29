package com.example.dagger_min.di

import androidx.lifecycle.ViewModel
import com.example.dagger_min.ViewModelKey
import com.example.dagger_min.ui.main.MainFragment
import com.example.dagger_min.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
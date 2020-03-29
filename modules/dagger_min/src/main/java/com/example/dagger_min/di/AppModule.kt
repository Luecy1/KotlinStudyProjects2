package com.example.dagger_min.di

import com.example.dagger_min.repo.GithubRepository
import com.example.dagger_min.repo.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository
}
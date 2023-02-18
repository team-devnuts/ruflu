package com.devnuts.ruflu.di

import com.devnuts.ruflu.data.repository.HomeRepositoryImpl
import com.devnuts.ruflu.data.repository.MainRepositoryImpl
import com.devnuts.ruflu.data.repository.SomeRepositoryImpl
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.domain.repository.MainRepository
import com.devnuts.ruflu.domain.repository.SomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindSomeRepository(
        someRepositoryImpl: SomeRepositoryImpl
    ): SomeRepository

    @Binds
    abstract fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}
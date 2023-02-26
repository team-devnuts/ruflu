package com.devnuts.ruflu.di

import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.data.source.SomeDataSource
import com.devnuts.ruflu.data.source.remote.HomeDataSourceImpl
import com.devnuts.ruflu.data.source.remote.SomeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    abstract fun bindHomeDataSource(
        homeDataSourceImpl: HomeDataSourceImpl
    ): HomeDataSource

    @Binds
    abstract fun bindSomeDataSource(
        someDataSourceImpl: SomeDataSourceImpl
    ): SomeDataSource
}
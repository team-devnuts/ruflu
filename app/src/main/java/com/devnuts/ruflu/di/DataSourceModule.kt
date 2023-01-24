package com.devnuts.ruflu.di

import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.data.source.remote.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    abstract fun bindMenuDataSource(
        homeDataSourceImpl: HomeDataSourceImpl
    ): HomeDataSource
}
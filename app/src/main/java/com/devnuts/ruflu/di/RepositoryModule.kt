package com.devnuts.ruflu.di

import com.devnuts.ruflu.data.repository.HomeRepositoryImpl
import com.devnuts.ruflu.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    abstract fun bindMenuRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}
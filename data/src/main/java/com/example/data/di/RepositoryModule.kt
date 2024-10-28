package com.example.data.di

import com.example.data.CrickInfoRepositoryImpl
import com.example.domain.repository.CrickInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCrickInfoRepository(
        crickInfoRepositoryImpl: CrickInfoRepositoryImpl): CrickInfoRepository
}
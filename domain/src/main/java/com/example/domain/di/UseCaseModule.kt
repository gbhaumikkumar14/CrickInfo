package com.example.domain.di

import com.example.domain.usecase.GetCurrentMatchesUseCase
import com.example.domain.usecase.GetCurrentMatchesUseCaseImpl
import com.example.domain.usecase.GetMatchInfoUseCase
import com.example.domain.usecase.GetMatchInfoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetCurrentMatchesUseCase(
        getCurrentMatchesUseCaseImpl: GetCurrentMatchesUseCaseImpl
    ): GetCurrentMatchesUseCase

    @Binds
    abstract fun bindGetMatchInfoUseCase(
        getMatchInfoUseCaseImpl: GetMatchInfoUseCaseImpl
    ): GetMatchInfoUseCase
}
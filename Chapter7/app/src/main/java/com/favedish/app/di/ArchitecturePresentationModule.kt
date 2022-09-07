package com.favedish.app.di

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.cleanarchitecture.usecase.UseCaseExecutor
import com.favedish.presentation.architecture.viewmodel.coroutine.AppCoroutineContextProvider
import com.favedish.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ArchitecturePresentationModule {
    @Provides
    fun providesCoroutineContextProvider(): CoroutineContextProvider =
        AppCoroutineContextProvider()

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor
}

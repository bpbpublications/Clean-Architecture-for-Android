package com.favedish.app.di

import com.favedish.domain.addnewdish.repository.AddNewDishRepository
import com.favedish.domain.addnewdish.usecase.AddNewDishUseCase
import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.presentation.addnewdish.mapper.NewDishPresentationToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewDishPresentationModule {
    @Provides
    fun providesNewDishPresentationToDomainMapper() = NewDishPresentationToDomainMapper()

    @Provides
    fun providesGetDishDetailsUseCase(
        addNewDishRepository: AddNewDishRepository,
        coroutineContextProvider: CoroutineContextProvider
    ) = AddNewDishUseCase(
        addNewDishRepository,
        coroutineContextProvider
    )
}

package com.favedish.app.di

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.restaurantdetails.repository.RestaurantDetailsRepository
import com.favedish.domain.restaurantdetails.usecase.GetDishesUseCase
import com.favedish.presentation.restaurantdetails.mapper.DishDomainToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RestaurantDetailsPresentationModule {
    @Provides
    fun providesGetDishesUseCase(
        restaurantDetailsRepository: RestaurantDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ) = GetDishesUseCase(
        restaurantDetailsRepository,
        coroutineContextProvider
    )

    @Provides
    fun providesDishDomainToPresentationMapper() =
        DishDomainToPresentationMapper()
}

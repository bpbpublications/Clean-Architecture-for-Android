package com.favedish.app.di

import com.favedish.domain.restaurantdetails.repository.RestaurantDetailsDummyRepository
import com.favedish.domain.restaurantdetails.repository.RestaurantDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RestaurantDetailsDataModule {
    @Provides
    fun providesRestaurantDetailsRepository(): RestaurantDetailsRepository =
        RestaurantDetailsDummyRepository()
}

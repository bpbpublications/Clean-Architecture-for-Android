package com.favedish.di

import com.favedish.screen.DishDetailsScreen
import com.favedish.screen.RestaurantDetailsScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ScreenModule {
    @Provides
    fun providesRestaurantDetailsScreen() = RestaurantDetailsScreen()

    @Provides
    fun providesDishDetailsScreen() = DishDetailsScreen()
}

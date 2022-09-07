package com.favedish.app.di

import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.datasource.dish.datasource.DishLiveDataSource
import com.favedish.datasource.dish.mapper.DishMemoryToResponseDataMapper
import com.favedish.datasource.dish.mapper.RestaurantDataToMemoryMapper
import com.favedish.datasource.dish.mapper.RestaurantMemoryToDataMapper
import com.favedish.datasource.dish.model.DishMemoryModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DishDataSourceModule {
    @Provides
    fun providesRestaurantMemoryToDataMapper() = RestaurantMemoryToDataMapper()

    @Provides
    fun providesRestaurantDataToMemoryMapper() = RestaurantDataToMemoryMapper()

    @Provides
    fun providesDishMemoryToDataMapper(
        restaurantMemoryToDataMapper: RestaurantMemoryToDataMapper
    ) = DishMemoryToResponseDataMapper(restaurantMemoryToDataMapper)

    @Provides
    @Singleton
    fun providesDishLiveDataSource(
        dishMemoryToResponseDataMapper: DishMemoryToResponseDataMapper
    ): DishDataSource = DishLiveDataSource(
        mutableMapOf(
            "#123" to DishMemoryModel(
                "#123",
                "Eggs Florentine",
                "Best I've had.",
                10,
                RestaurantMemoryModel("Egg-citing")
            )
        ),
        dishMemoryToResponseDataMapper
    )
}

package com.favedish.app.di

import android.content.res.Resources
import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dishdetails.mapper.DishDataToDomainMapper
import com.favedish.data.dishdetails.mapper.RestaurantDataToDomainMapper
import com.favedish.data.dishdetails.repository.DishDetailsLiveRepository
import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.dishdetails.repository.DishDetailsRepository
import com.favedish.domain.dishdetails.usecase.GetDishDetailsUseCase
import com.favedish.presentation.dishdetails.mapper.DishDetailsDomainToPresentationMapper
import com.favedish.presentation.dishdetails.mapper.RestaurantDomainToPresentationMapper
import com.favedish.presentation.dishdetails.model.DishDetailsViewState
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.ViewsProvider
import com.favedish.ui.dishdetails.binder.DishDetailsViewStateBinder
import com.favedish.ui.dishdetails.mapper.DishDetailsNotificationPresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DishDetailsModule {
    @Provides
    fun providesRestaurantDataToDomainMapper() = RestaurantDataToDomainMapper()

    @Provides
    fun providesDishDataToDomainMapper(
        restaurantDataToDomainMapper: RestaurantDataToDomainMapper
    ) = DishDataToDomainMapper(restaurantDataToDomainMapper)

    @Provides
    fun providesDishDetailsRepository(
        dishDataSource: DishDataSource,
        dishDataToDomainMapper: DishDataToDomainMapper
    ): DishDetailsRepository = DishDetailsLiveRepository(
        dishDataSource,
        dishDataToDomainMapper
    )

    @Provides
    fun providesGetDishDetailsUseCase(
        dishDetailsRepository: DishDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ) = GetDishDetailsUseCase(
        dishDetailsRepository,
        coroutineContextProvider
    )

    @Provides
    fun providesRestaurantDomainToPresentationMapper() =
        RestaurantDomainToPresentationMapper()

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesDishDetailsViewStateBinder(resources: Resources) =
        DishDetailsViewStateBinder(resources)
            as ViewStateBinder<DishDetailsViewState, ViewsProvider>

    @Provides
    fun providesDishDetailsNotificationPresentationToUiMapper() =
        DishDetailsNotificationPresentationToUiMapper()

    @Provides
    fun providesDishDetailsDomainToPresentationMapper(
        restaurantDomainToPresentationMapper: RestaurantDomainToPresentationMapper
    ) = DishDetailsDomainToPresentationMapper(
        restaurantDomainToPresentationMapper
    )
}

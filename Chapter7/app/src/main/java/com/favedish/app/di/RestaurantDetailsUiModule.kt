package com.favedish.app.di

import androidx.fragment.app.Fragment
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsViewState
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.ViewsProvider
import com.favedish.ui.restaurantdetails.binder.RestaurantDetailsViewStateBinder
import com.favedish.ui.restaurantdetails.mapper.DishPresentationToUiMapper
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsNotificationPresentationToUiMapper
import com.favedish.ui.restaurantdetails.view.RestaurantDetailsFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class RestaurantDetailsUiModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesRestaurantDetailsViewStateBinder(
        fragment: Fragment,
        dishPresentationToUiMapper: DishPresentationToUiMapper
    ) = RestaurantDetailsViewStateBinder(
        fragment as RestaurantDetailsFragment,
        dishPresentationToUiMapper
    ) as ViewStateBinder<RestaurantDetailsViewState, ViewsProvider>

    @Provides
    fun providesRestaurantDetailsNotificationPresentationToUiMapper() =
        RestaurantDetailsNotificationPresentationToUiMapper()

    @Provides
    fun providesDishPresentationToUiMapper() =
        DishPresentationToUiMapper()
}

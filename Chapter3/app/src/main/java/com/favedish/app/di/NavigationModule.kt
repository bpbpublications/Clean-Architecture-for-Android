package com.favedish.app.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.favedish.R
import com.favedish.app.navigation.AppDishDetailsDestinationToUiMapper
import com.favedish.app.navigation.AppRestaurantDetailsDestinationToUiMapper
import com.favedish.navigation.mapper.GlobalDestinationToUiMapper
import com.favedish.ui.dishdetails.mapper.DishDetailsDestinationToUiMapper
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsDestinationToUiMapper
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {
    @Provides
    fun providesSupportFragmentManager(activity: Activity) =
        (activity as AppCompatActivity).supportFragmentManager

    @Provides
    fun providesNavHostFragment(fragmentManager: FragmentManager) =
        fragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment

    @Provides
    fun providesNavController(navHostFragment: NavHostFragment) =
        navHostFragment.navController

    @Provides
    fun providesGlobalDestinationToUiMapper(
        lazyNavController: Lazy<NavController>
    ) = GlobalDestinationToUiMapper(lazyNavController)

    @Provides
    fun providesAppDishDetailsDestinationToUiMapper(
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): DishDetailsDestinationToUiMapper =
        AppDishDetailsDestinationToUiMapper(globalDestinationToUiMapper)

    @Provides
    fun providesAppRestaurantDetailsDestinationToUiMapper(
        fragmentManager: FragmentManager,
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): RestaurantDetailsDestinationToUiMapper =
        AppRestaurantDetailsDestinationToUiMapper(fragmentManager, globalDestinationToUiMapper)
}

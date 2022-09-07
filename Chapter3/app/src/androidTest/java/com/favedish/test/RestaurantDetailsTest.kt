package com.favedish.test

import androidx.core.os.bundleOf
import com.favedish.screen.DishDetailsScreen
import com.favedish.screen.RestaurantDetailsScreen
import com.favedish.testing.test.BaseTest
import com.favedish.testing.test.BaseTest.TestLauncher.WithFragment
import com.favedish.ui.restaurantdetails.view.RestaurantDetailsFragment
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class RestaurantDetailsTest : BaseTest() {
    override val testLauncher =
        WithFragment(
            RestaurantDetailsFragment::class,
            bundleOf("RestaurantId" to "REST1")
        )

    @Inject
    lateinit var restaurantDetailsScreen: RestaurantDetailsScreen

    @Inject
    lateinit var dishDetailsScreen: DishDetailsScreen

    @Test
    fun givenDishesWhenTappingDishThenNavigatesToDishDetails() {
        val dishName = "Eggs Florentine"
        with(restaurantDetailsScreen) {
            tapOnDish(dishName)
        }
        with(dishDetailsScreen) {
            seeDishName(dishName)
        }
    }
}

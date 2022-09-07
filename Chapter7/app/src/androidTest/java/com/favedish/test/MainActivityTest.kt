package com.favedish.test

import com.favedish.screen.RestaurantDetailsScreen
import com.favedish.testing.test.BaseTest
import com.favedish.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainActivityTest : BaseTest() {
    override val testLauncher = TestLauncher.FromClass(MainActivity::class)

    @Inject
    lateinit var restaurantDetailsScreen: RestaurantDetailsScreen

    @Test
    fun givenDishesWhenOnRestaurantDetailsThenSeesDishes() {
        with(restaurantDetailsScreen) {
            seeDishes("Eggs Florentine", "Couscous")
        }
    }
}

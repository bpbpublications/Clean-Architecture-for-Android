package com.favedish.test

import androidx.core.os.bundleOf
import com.favedish.screen.DishDetailsScreen
import com.favedish.testing.test.BaseTest
import com.favedish.ui.dishdetails.view.DishDetailsFragment
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class DishDetailsTest : BaseTest() {
    override val testLauncher =
        TestLauncher.WithFragment(
            DishDetailsFragment::class,
            bundleOf("DishId" to "#123")
        )

    @Inject
    lateinit var dishDetailsScreen: DishDetailsScreen

    @Test
    fun givenDishIdWhenOnDishDetailsThenSeesDishName() {
        val dishName = "Eggs Florentine"
        with(dishDetailsScreen) {
            seeDishName(dishName)
        }
    }
}

package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class RestaurantMemoryToDataMapperTest(
    private val givenRestaurant: RestaurantMemoryModel,
    private val expectedRestaurant: RestaurantDataModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase("The Plate"),
            testCase("La Plaque")
        )

        private fun testCase(restaurantName: String) = arrayOf(
            RestaurantMemoryModel(restaurantName),
            RestaurantDataModel(restaurantName)
        )
    }

    private lateinit var classUnderTest: RestaurantMemoryToDataMapper

    @Before
    fun setUp() {
        classUnderTest = RestaurantMemoryToDataMapper()
    }

    @Test
    fun `Given memory restaurant when toData then returns expected Data restaurant`() {
        // When
        val actualValue = classUnderTest.toData(givenRestaurant)

        // Then
        assertEquals(expectedRestaurant, actualValue)
    }
}

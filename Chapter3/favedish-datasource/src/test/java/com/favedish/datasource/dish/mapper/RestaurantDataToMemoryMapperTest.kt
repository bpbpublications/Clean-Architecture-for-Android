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
class RestaurantDataToMemoryMapperTest(
    private val givenRestaurant: RestaurantDataModel,
    private val expectedRestaurant: RestaurantMemoryModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase("La Isla"),
            testCase("Bonita")
        )

        private fun testCase(restaurantName: String) = arrayOf(
            RestaurantDataModel(restaurantName),
            RestaurantMemoryModel(restaurantName)
        )
    }

    private lateinit var classUnderTest: RestaurantDataToMemoryMapper

    @Before
    fun setUp() {
        classUnderTest = RestaurantDataToMemoryMapper()
    }

    @Test
    fun `Given Data restaurant when toMemory then returns expected memory restaurant`() {
        // When
        val actualValue = classUnderTest.toMemory(givenRestaurant)

        // Then
        assertEquals(expectedRestaurant, actualValue)
    }
}

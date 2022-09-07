package com.favedish.data.dishdetails.mapper

import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class RestaurantDataToDomainMapperTest(
    private val givenRestaurant: RestaurantDataModel,
    private val expectedRestaurant: RestaurantDomainModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase("Food For All"),
            testCase("Food For Soul")
        )

        private fun testCase(restaurantName: String) =
            arrayOf(
                RestaurantDataModel(restaurantName),
                RestaurantDomainModel(restaurantName)
            )
    }

    private lateinit var classUnderTest: RestaurantDataToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = RestaurantDataToDomainMapper()
    }

    @Test
    fun `Given Data restaurant when toDomain then returns expected Domain restaurant`() {
        // When
        val actualValue = classUnderTest.toDomain(givenRestaurant)

        // Then
        assertEquals(expectedRestaurant, actualValue)
    }
}

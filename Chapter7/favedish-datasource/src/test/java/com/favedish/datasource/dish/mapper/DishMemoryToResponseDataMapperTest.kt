package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.model.DishMemoryModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.MethodRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.given

@RunWith(Parameterized::class)
class DishMemoryToResponseDataMapperTest(
    private val givenDish: DishMemoryModel,
    private val expectedDish: DishResponseDataModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase(
                dishId = "DISH01",
                dishName = "Pad Thai",
                comment = "Yum",
                rating = 5,
                restaurantName = "Thai Food"
            ),
            testCase(
                dishId = "DISH02",
                dishName = "Rice",
                comment = "Rather plain",
                rating = 1,
                restaurantName = "Just Rice"
            )
        )

        private fun testCase(
            dishId: String,
            dishName: String,
            comment: String,
            rating: Int,
            restaurantName: String
        ) = arrayOf(
            DishMemoryModel(
                id = dishId,
                name = dishName,
                comment = comment,
                rating = rating,
                RestaurantMemoryModel(restaurantName)
            ),
            DishResponseDataModel(
                id = dishId,
                name = dishName,
                comment = comment,
                rating = rating,
                restaurant = RestaurantDataModel(restaurantName)
            )
        )
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: DishMemoryToResponseDataMapper

    @Mock
    private lateinit var restaurantMemoryToDataMapper: RestaurantMemoryToDataMapper

    @Before
    fun setUp() {
        classUnderTest = DishMemoryToResponseDataMapper(restaurantMemoryToDataMapper)
    }

    @Test
    fun `Given memory dish when toData then returns expected Data dish`() {
        // Given
        given(restaurantMemoryToDataMapper.toData(givenDish.restaurant))
            .willReturn(expectedDish.restaurant)

        // When
        val actualValue = classUnderTest.toData(givenDish)

        // Then
        assertEquals(expectedDish, actualValue)
    }
}

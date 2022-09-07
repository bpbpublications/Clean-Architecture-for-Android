package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.DishRequestDataModel
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
class DishRequestDataToMemoryResolverTest(
    private val givenDish: DishRequestDataModel,
    private val givenDishId: String,
    private val givenRestaurant: RestaurantDataModel,
    private val expectedDish: DishMemoryModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase(
                dishName = "Pasta",
                restaurantId = "RES01",
                dishId = "PAS01",
                restaurantName = "Italiana"
            ),
            testCase(
                dishName = "Shrimps",
                restaurantId = "RES02",
                dishId = "SHR01",
                restaurantName = "SeeFood"
            )
        )

        private fun testCase(
            dishName: String,
            restaurantId: String,
            dishId: String,
            restaurantName: String
        ) = arrayOf(
            DishRequestDataModel(
                name = dishName,
                restaurantId = restaurantId
            ),
            dishId,
            RestaurantDataModel(restaurantName),
            DishMemoryModel(
                id = dishId,
                name = dishName,
                comment = "",
                rating = 0,
                restaurant = RestaurantMemoryModel(restaurantName)
            )
        )
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: DishRequestDataToMemoryResolver

    @Mock
    private lateinit var restaurantDataToMemoryMapper: RestaurantDataToMemoryMapper

    @Before
    fun setUp() {
        classUnderTest = DishRequestDataToMemoryResolver(restaurantDataToMemoryMapper)
    }

    @Test
    fun `Given MapperInput when toMemory then returns expected memory dish`() {
        // Given
        given(restaurantDataToMemoryMapper.toMemory(givenRestaurant))
            .willReturn(expectedDish.restaurant)

        // When
        val actualValue = classUnderTest.toMemory(givenDish, givenDishId, givenRestaurant)

        // Then
        assertEquals(expectedDish, actualValue)
    }
}

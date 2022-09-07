package com.favedish.data.dishdetails.mapper

import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
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
class DishDataToDomainMapperTest(
    private val givenDish: DishResponseDataModel,
    private val expectedDish: DishDomainModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase(
                dishId = "321",
                dishName = "Sunday Special",
                comment = "What's so special about it?",
                rating = 1,
                restaurantName = "Chop Chop"
            ),
            testCase(
                dishId = "123",
                dishName = "Night Delight",
                comment = "Delightful.",
                rating = 4,
                restaurantName = "Al Akel"
            )
        )

        private fun testCase(
            dishId: String,
            dishName: String,
            comment: String,
            rating: Int,
            restaurantName: String
        ) = arrayOf(
            DishResponseDataModel(
                id = dishId,
                name = dishName,
                comment = comment,
                rating = rating,
                restaurant = RestaurantDataModel(restaurantName)
            ),
            DishDomainModel(
                id = dishId,
                name = dishName,
                comment = comment,
                rating = rating,
                restaurant = RestaurantDomainModel(restaurantName)
            )
        )
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: DishDataToDomainMapper

    @Mock
    private lateinit var restaurantDataToDomainMapper: RestaurantDataToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = DishDataToDomainMapper(restaurantDataToDomainMapper)
    }

    @Test
    fun `Given Data dish when toDomain then returns expected Domain dish`() {
        // Given
        given(restaurantDataToDomainMapper.toDomain(givenDish.restaurant))
            .willReturn(expectedDish.restaurant)

        // When
        val actualValue = classUnderTest.toDomain(givenDish)

        // Then
        assertEquals(expectedDish, actualValue)
    }
}

package com.favedish.presentation.dishdetails.mapper

import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
import com.favedish.presentation.dishdetails.model.DishDetailsPresentationModel
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
class DishDetailsDomainToPresentationMapperTest(
    private val givenInput: DishDomainModel,
    private val expected: DishDetailsPresentationModel
) {
    companion object {
        private const val DISH_ID_1 = "ID1"
        private const val DISH_NAME_1 = "Piri Piri Chicken"
        private const val DISH_COMMENT_1 = "Great spicing."
        private const val DISH_RATING_1 = 5
        private const val RESTAURANT_NAME_1 = "City Chicken"
        private const val DISH_ID_2 = "ID2"
        private const val DISH_NAME_2 = "Cauliflower Steak"
        private const val DISH_COMMENT_2 = "So dry."
        private const val DISH_RATING_2 = 1
        private const val RESTAURANT_NAME_2 = "Vegan Fever"

        @JvmStatic
        @Parameters(name = "Given {0} then returns {1}")
        fun data(): Iterable<Array<Any>> = listOf(
            testCase(
                dishId = DISH_ID_1,
                dishName = DISH_NAME_1,
                dishComment = DISH_COMMENT_1,
                dishRating = DISH_RATING_1,
                restaurantName = RESTAURANT_NAME_1
            ),
            testCase(
                dishId = DISH_ID_2,
                dishName = DISH_NAME_2,
                dishComment = DISH_COMMENT_2,
                dishRating = DISH_RATING_2,
                restaurantName = RESTAURANT_NAME_2
            )
        )

        private fun testCase(
            dishId: String,
            dishName: String,
            dishComment: String,
            dishRating: Int,
            restaurantName: String
        ) = arrayOf(
            DishDomainModel(
                id = dishId,
                name = dishName,
                comment = dishComment,
                rating = dishRating,
                restaurant = RestaurantDomainModel(
                    name = restaurantName
                )
            ),
            DishDetailsPresentationModel(
                id = dishId,
                name = dishName,
                comment = dishComment,
                rating = dishRating,
                restaurant = RestaurantPresentationModel(
                    name = restaurantName
                )
            )
        )
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: DishDetailsDomainToPresentationMapper

    @Mock
    private lateinit var restaurantDomainToPresentationMapper:
        RestaurantDomainToPresentationMapper

    @Before
    fun setUp() {
        classUnderTest = DishDetailsDomainToPresentationMapper(
            restaurantDomainToPresentationMapper
        )
    }

    @Test
    fun `When toPresentation`() {
        // Given
        given(
            restaurantDomainToPresentationMapper
                .toPresentation(givenInput.restaurant)
        ).willReturn(expected.restaurant)

        // When
        val actualValue = classUnderTest.toPresentation(givenInput)

        // Then
        assertEquals(expected, actualValue)
    }
}

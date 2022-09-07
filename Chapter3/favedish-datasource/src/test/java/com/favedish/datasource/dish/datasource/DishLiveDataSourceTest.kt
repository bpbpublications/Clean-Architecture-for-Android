package com.favedish.datasource.dish.datasource

import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.mapper.DishMemoryToResponseDataMapper
import com.favedish.datasource.dish.model.DishMemoryModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class DishLiveDataSourceTest {
    private lateinit var classUnderTest: DishLiveDataSource

    private val dishStore: MutableMap<String, DishMemoryModel> = mutableMapOf()

    @Mock
    private lateinit var dishMemoryToResponseDataMapper: DishMemoryToResponseDataMapper

    @Before
    fun setUp() {
        classUnderTest = DishLiveDataSource(
            dishStore,
            dishMemoryToResponseDataMapper
        )
    }

    @Test
    fun `Given dish ID when dishDetails then returns dish`() {
        // Given
        val dishId = "123"
        val dishName = "Spaghetti Pomadoro"
        val dishComment = "Best pasta dish ever!"
        val dishRating = 5
        val restaurantName = "Perfecto"
        val memoryDish = DishMemoryModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantMemoryModel(restaurantName)
        )
        dishStore[dishId] = memoryDish
        val expected = DishResponseDataModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantDataModel(restaurantName)
        )
        given(dishMemoryToResponseDataMapper.toData(memoryDish))
            .willReturn(expected)

        // When
        val actualValue = classUnderTest.dishDetails(dishId)

        // Then
        assertEquals(expected, actualValue)
    }

    @Test
    fun `Given no matching dish when dishDetails then throws exception`() {
        // Given
        val expectedDishId = "UNKNOWN_DISH"
        val expectedException = IllegalArgumentException(
            "Dish $expectedDishId not found"
        )
        Int.MAX_VALUE

        // When
        val actualException = try {
            classUnderTest.dishDetails(expectedDishId)
            null
        } catch (illegalArgumentException: IllegalArgumentException) {
            illegalArgumentException
        }

        // Then
        assertTrue(actualException is IllegalArgumentException)
        assertEquals(expectedException.message, actualException?.message)
    }
}

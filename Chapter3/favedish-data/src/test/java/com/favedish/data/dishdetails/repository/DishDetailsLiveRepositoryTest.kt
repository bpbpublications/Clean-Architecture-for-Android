package com.favedish.data.dishdetails.repository

import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.data.dishdetails.mapper.DishDataToDomainMapper
import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class DishDetailsLiveRepositoryTest {
    private lateinit var classUnderTest: DishDetailsLiveRepository

    @Mock
    private lateinit var dishDataSource: DishDataSource

    @Mock
    private lateinit var dishDataToDomainMapper: DishDataToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = DishDetailsLiveRepository(
            dishDataSource,
            dishDataToDomainMapper
        )
    }

    @Test
    fun `Given dish ID when dishDetails then returns dish details`() {
        // Given
        val dishId = "dishId"
        val dishName = "Spaghetti Pomadoro"
        val dishComment = "Best pasta dish ever!"
        val dishRating = 5
        val restaurantName = "Perfecto"
        val dataDish = DishResponseDataModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantDataModel(restaurantName)
        )
        given(dishDataSource.dishDetails(dishId))
            .willReturn(dataDish)
        val expected = DishDomainModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantDomainModel(restaurantName)
        )
        given(dishDataToDomainMapper.toDomain(dataDish))
            .willReturn(expected)

        // When
        val actualValue = classUnderTest.dishDetails(dishId)

        // Then
        assertEquals(expected, actualValue)
    }
}

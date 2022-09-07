package com.favedish.domain.dishdetails.usecase

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
import com.favedish.domain.dishdetails.repository.DishDetailsRepository
import com.favedish.domain.test.coroutine.FakeCoroutineContextProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class GetDishDetailsUseCaseTest {
    private lateinit var getDishDetailsUseCase: GetDishDetailsUseCase

    @Mock
    private lateinit var dishDetailsRepository: DishDetailsRepository

    private lateinit var coroutineContextProvider: CoroutineContextProvider

    @Before
    fun setUp() {
        coroutineContextProvider = FakeCoroutineContextProvider

        getDishDetailsUseCase = GetDishDetailsUseCase(
            dishDetailsRepository,
            coroutineContextProvider
        )
    }

    @Test
    fun `Given dish ID when executeInBackground then returns matching dish`() {
        // Given
        val givenDishId = "DISH-ID"
        val expectedDish = DishDomainModel(
            id = "DishId",
            name = "Name",
            comment = "Comment",
            rating = 1,
            restaurant = RestaurantDomainModel("Bon Apetite")
        )
        given(dishDetailsRepository.dishDetails(givenDishId))
            .willReturn(expectedDish)

        // When
        val actualResult = getDishDetailsUseCase.executeInBackground(givenDishId)

        // Then
        assertEquals(expectedDish, actualResult)
    }
}

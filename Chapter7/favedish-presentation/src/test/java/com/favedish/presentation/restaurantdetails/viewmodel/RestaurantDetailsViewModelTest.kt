package com.favedish.presentation.restaurantdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favedish.domain.cleanarchitecture.usecase.UseCaseExecutor
import com.favedish.domain.restaurantdetails.model.DishDomainModel
import com.favedish.domain.restaurantdetails.usecase.GetDishesUseCase
import com.favedish.presentation.restaurantdetails.mapper.DishDomainToPresentationMapper
import com.favedish.presentation.restaurantdetails.model.DishPresentationModel
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsPresentationDestination.AddNewDish
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsPresentationDestination.DishDetails
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsViewState
import com.favedish.test.viewmodel.givenUseCaseSuccess
import com.favedish.test.viewmodel.observeDestinationForever
import com.favedish.test.viewmodel.observeViewStateForever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: TestRule =
        InstantTaskExecutorRule()

    private lateinit var classUnderTest: RestaurantDetailsViewModel

    @Mock
    private lateinit var getDishesUseCase: GetDishesUseCase

    @Mock
    private lateinit var dishDomainToPresentationMapper:
        DishDomainToPresentationMapper

    @Mock
    private lateinit var useCaseExecutor: UseCaseExecutor

    @Before
    fun setUp() {
        classUnderTest = RestaurantDetailsViewModel(
            getDishesUseCase,
            dishDomainToPresentationMapper
        ) { useCaseExecutor }
    }

    @Test
    fun `Given no action when view state observed then presents default state`() {
        // Given
        val expectedViewStates =
            listOf(RestaurantDetailsViewState())

        // When
        val actualViewStates = classUnderTest.observeViewStateForever()

        // Then
        assertEquals(expectedViewStates, actualViewStates)
    }

    @Test
    fun `Given restaurantId, dishes when onEntered then presents loading, dish list`() {
        // Given
        val restaurantId = "REST007"

        val givenDish1 = DishDomainModel("1", "Nice dish")
        val givenDish2 = DishDomainModel("2", "Yucky dish")
        val givenDishes = setOf(givenDish1, givenDish2)

        val expectedDish1 = DishPresentationModel("1", "Nice dish")
        given(dishDomainToPresentationMapper.toPresentation(givenDish1)).willReturn(expectedDish1)
        val expectedDish2 = DishPresentationModel("2", "Yucky dish")
        given(dishDomainToPresentationMapper.toPresentation(givenDish2)).willReturn(expectedDish2)
        val expectedDishes = listOf(expectedDish1, expectedDish2)

        useCaseExecutor.givenUseCaseSuccess(getDishesUseCase, restaurantId, givenDishes)

        val expectedViewStates = listOf(
            RestaurantDetailsViewState(isLoading = true),
            RestaurantDetailsViewState(dishes = expectedDishes)
        )

        val actualViewStates = classUnderTest.observeViewStateForever()
        actualViewStates.clear()

        // When
        classUnderTest.onEntered(restaurantId)

        // Then
        assertEquals(expectedViewStates, actualViewStates)
    }

    @Test
    fun `Given dish ID when onDishDetailsAction then navigates to dish details`() {
        // Given
        val dishId = "Cheese743"

        val expectedDestinations = listOf(DishDetails(dishId))

        val actualDestinations = classUnderTest.observeDestinationForever()
        actualDestinations.clear()

        // When
        classUnderTest.onDishDetailsAction(dishId)

        // Then
        assertEquals(expectedDestinations, actualDestinations)
    }

    @Test
    fun `When onAddNewDishAction then navigates to add new dish`() {
        // Given
        val restaurantId = "REST007"

        val expectedDestinations = listOf(AddNewDish(restaurantId))

        val actualDestinations = classUnderTest.observeDestinationForever()
        actualDestinations.clear()

        // When
        classUnderTest.onAddNewDishAction(restaurantId)

        // Then
        assertEquals(expectedDestinations, actualDestinations)
    }
}

package com.favedish.presentation.dishdetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favedish.domain.cleanarchitecture.usecase.UseCaseExecutor
import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel
import com.favedish.domain.dishdetails.usecase.GetDishDetailsUseCase
import com.favedish.presentation.dishdetails.mapper.DishDetailsDomainToPresentationMapper
import com.favedish.presentation.dishdetails.mapper.RestaurantPresentationModel
import com.favedish.presentation.dishdetails.model.DishDetailsPresentationModel
import com.favedish.presentation.dishdetails.model.DishDetailsViewState
import com.favedish.test.viewmodel.givenUseCaseSuccess
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
class DishDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: TestRule =
        InstantTaskExecutorRule()

    private lateinit var classUnderTest: DishDetailsViewModel

    @Mock
    private lateinit var getDishDetailsUseCase: GetDishDetailsUseCase

    @Mock
    private lateinit var dishDetailsDomainToPresentationMapper:
        DishDetailsDomainToPresentationMapper

    @Mock
    private lateinit var useCaseExecutor: UseCaseExecutor

    @Before
    fun setUp() {
        classUnderTest = DishDetailsViewModel(
            getDishDetailsUseCase,
            dishDetailsDomainToPresentationMapper
        ) { useCaseExecutor }
    }

    @Test
    fun `Given no action when view state observed then presents default state`() {
        // Given
        val expectedViewStates =
            listOf(DishDetailsViewState())

        val actualViewStates = mutableListOf<DishDetailsViewState>()

        // When
        classUnderTest.viewState.observeForever(actualViewStates::add)

        // Then
        assertEquals(expectedViewStates, actualViewStates)
    }

    @Test
    fun `Given dish ID when onEntered then presents loading, dish details`() {
        // Given
        val dishId = "dishId"

        val dishName = "Spaghetti Pomadoro"
        val dishComment = "Best pasta dish ever!"
        val dishRating = 5
        val restaurantName = "Perfecto"
        val domainDish = DishDomainModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantDomainModel(restaurantName)
        )
        useCaseExecutor.givenUseCaseSuccess(
            useCase = getDishDetailsUseCase,
            input = dishId,
            output = domainDish
        )
        val presentationDish = DishDetailsPresentationModel(
            id = dishId,
            name = dishName,
            comment = dishComment,
            rating = dishRating,
            restaurant = RestaurantPresentationModel(restaurantName)
        )
        given(
            dishDetailsDomainToPresentationMapper
                .toPresentation(domainDish)
        ).willReturn(presentationDish)

        val actualViewStates = mutableListOf<DishDetailsViewState>()
        classUnderTest.viewState.observeForever(actualViewStates::add)
        actualViewStates.clear()

        val expectedViewStates = listOf(
            DishDetailsViewState(isLoading = true),
            DishDetailsViewState(
                isLoading = false,
                dishDetails = presentationDish
            )
        )

        // When
        classUnderTest.onEntered(dishId)

        // Then
        assertEquals(expectedViewStates, actualViewStates)
    }
}

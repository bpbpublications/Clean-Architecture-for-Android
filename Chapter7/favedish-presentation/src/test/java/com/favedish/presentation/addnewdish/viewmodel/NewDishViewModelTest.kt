package com.favedish.presentation.addnewdish.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.domain.addnewdish.usecase.AddNewDishUseCase
import com.favedish.domain.cleanarchitecture.usecase.UseCaseExecutor
import com.favedish.presentation.addnewdish.destination.NewDishPresentationDestination.NewDishCreationSuccess
import com.favedish.presentation.addnewdish.mapper.NewDishPresentationToDomainMapper
import com.favedish.presentation.addnewdish.model.NewDishPresentationModel
import com.favedish.presentation.architecture.model.PresentationDestination
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
class NewDishViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: TestRule =
        InstantTaskExecutorRule()

    private lateinit var classUnderTest: NewDishViewModel

    @Mock
    private lateinit var addNewDishUseCase: AddNewDishUseCase

    @Mock
    private lateinit var newDishPresentationToDomainMapper: NewDishPresentationToDomainMapper

    @Mock
    private lateinit var useCaseExecutor: UseCaseExecutor

    @Before
    fun setUp() {
        classUnderTest = NewDishViewModel(
            addNewDishUseCase,
            newDishPresentationToDomainMapper
        ) { useCaseExecutor }
    }

    @Test
    fun `Given new dish when onAddNewDishAction then navigates to success`() {
        // Given
        val dishName = "Salad"
        val restaurantId = "HEALTH01"
        val presentationDish = NewDishPresentationModel(
            name = dishName,
            restaurantId = restaurantId
        )
        val domainDish = NewDishDomainModel(
            name = dishName,
            restaurantId = restaurantId
        )
        given(newDishPresentationToDomainMapper.toDomain(presentationDish))
            .willReturn(domainDish)

        val newDishId = "NEW_DISH"
        useCaseExecutor.givenUseCaseSuccess(addNewDishUseCase, domainDish, newDishId)

        val actualDestinations = mutableListOf<PresentationDestination>()

        // When
        classUnderTest.destination.observeForever(actualDestinations::add)
        val expectedDestinations = listOf(
            NewDishCreationSuccess(newDishId
            )
        )

        // When
        classUnderTest.onAddNewDishAction(presentationDish)

        // Then
        assertEquals(expectedDestinations, actualDestinations)
    }
}

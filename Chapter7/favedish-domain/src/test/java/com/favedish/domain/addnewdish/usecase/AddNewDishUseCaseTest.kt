package com.favedish.domain.addnewdish.usecase

import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.domain.addnewdish.repository.AddNewDishRepository
import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.test.coroutine.FakeCoroutineContextProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class AddNewDishUseCaseTest {
    private lateinit var classUnderTest: AddNewDishUseCase

    @Mock
    private lateinit var addNewDishRepository: AddNewDishRepository

    private lateinit var coroutineContextProvider: CoroutineContextProvider

    @Before
    fun setUp() {
        coroutineContextProvider = FakeCoroutineContextProvider

        classUnderTest = AddNewDishUseCase(
            addNewDishRepository,
            coroutineContextProvider
        )
    }

    @Test
    fun `Given new dish when executeInBackground then returns added dish ID`() {
        // Given
        val expectedDishId = "CAT_01"
        val request = NewDishDomainModel("Candy", "RestId")
        given(addNewDishRepository.addNewDish(request)).willReturn(expectedDishId)

        // When
        val actualValue = classUnderTest.executeInBackground(request)

        // Then
        assertEquals(expectedDishId, actualValue)
    }
}

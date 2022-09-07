package com.favedish.data.addnewdish.repository

import com.favedish.data.addnewdish.mapper.NewDishDomainToDishRequestDataMapper
import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.domain.addnewdish.model.NewDishDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class AddNewDishLiveRepositoryTest {
    private lateinit var classUnderTest: AddNewDishLiveRepository

    @Mock
    private lateinit var dishDataSource: DishDataSource

    @Mock
    private lateinit var newDishDomainToDishRequestDataMapper: NewDishDomainToDishRequestDataMapper

    @Before
    fun setUp() {
        classUnderTest =
            AddNewDishLiveRepository(dishDataSource, newDishDomainToDishRequestDataMapper)
    }

    @Test
    fun `Given new dish when addNewDish then returns new dish ID`() {
        // Given
        val restaurantId = "RES01"
        val dishName = "Pasta"
        val newDish = NewDishDomainModel(name = dishName, restaurantId = restaurantId)
        val dishRequest = DishRequestDataModel(name = dishName, restaurantId = restaurantId)
        given(newDishDomainToDishRequestDataMapper.toData(newDish))
            .willReturn(dishRequest)
        val expectedDishId = "DISH01"
        given(dishDataSource.addNewDish(dishRequest))
            .willReturn(expectedDishId)

        // When
        val actualValue = classUnderTest.addNewDish(newDish)

        // Then
        assertEquals(expectedDishId, actualValue)
    }
}

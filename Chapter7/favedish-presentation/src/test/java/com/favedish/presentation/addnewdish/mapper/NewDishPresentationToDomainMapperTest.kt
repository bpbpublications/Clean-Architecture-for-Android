package com.favedish.presentation.addnewdish.mapper

import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.presentation.addnewdish.model.NewDishPresentationModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class NewDishPresentationToDomainMapperTest(
    private val givenNewDish: NewDishPresentationModel,
    private val expectedNewDish: NewDishDomainModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase("Meatballs", "MB1"),
            testCase("Lettuce", "VG1")
        )

        private fun testCase(dishName: String, restaurantId: String) = arrayOf(
            NewDishPresentationModel(name = dishName, restaurantId = restaurantId),
            NewDishDomainModel(name = dishName, restaurantId = restaurantId)
        )
    }

    private lateinit var classUnderTest: NewDishPresentationToDomainMapper

    @Before
    fun setUp() {
        classUnderTest = NewDishPresentationToDomainMapper()
    }

    @Test
    fun `Given Presentation dish when toDomain then returns expected Domain dish`() {
        // When
        val actualValue = classUnderTest.toDomain(givenNewDish)

        // Then
        assertEquals(expectedNewDish, actualValue)
    }
}

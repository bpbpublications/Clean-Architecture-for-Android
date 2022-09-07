package com.favedish.data.addnewdish.mapper

import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.domain.addnewdish.model.NewDishDomainModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class NewDishDomainToDishRequestDataMapperTest(
    private val givenDish: NewDishDomainModel,
    private val expectedDish: DishRequestDataModel
) {
    companion object {
        @JvmStatic
        @Parameters
        fun data(): Collection<Array<*>> = listOf(
            testCase("Mashed Potatoes", "R007"),
            testCase("Jacket Potatoes", "R00")
        )

        private fun testCase(dishName: String, restaurantId: String) =
            arrayOf(
                NewDishDomainModel(name = dishName, restaurantId = restaurantId),
                DishRequestDataModel(name = dishName, restaurantId = restaurantId)
            )
    }

    private lateinit var classUnderTest: NewDishDomainToDishRequestDataMapper

    @Before
    fun setUp() {
        classUnderTest = NewDishDomainToDishRequestDataMapper()
    }

    @Test
    fun `Given domain dish when toData then returns expected data dish`() {
        // When
        val actualValue = classUnderTest.toData(givenDish)

        // Then
        assertEquals(expectedDish, actualValue)
    }
}

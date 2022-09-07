package com.favedish.ui.addnewdish.mapper

import com.favedish.presentation.addnewdish.model.NewDishPresentationNotification
import org.junit.Before
import org.junit.Test

class NewDishNotificationPresentationToUiMapperTest {
    private lateinit var classUnderTest: NewDishNotificationPresentationToUiMapper

    @Before
    fun setUp() {
        classUnderTest = NewDishNotificationPresentationToUiMapper()
    }

    @Test(expected = IllegalStateException::class)
    fun `When toUi then throws exception`() {
        // Given
        val presentationNotification = NewDishPresentationNotification()

        // When
        classUnderTest.toUi(presentationNotification)

        // Then
        // IllegalStateException is thrown
    }
}

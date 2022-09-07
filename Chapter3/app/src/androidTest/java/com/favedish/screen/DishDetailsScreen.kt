package com.favedish.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

class DishDetailsScreen {
    fun seeDishName(dishName: String) {
        onView(withText(dishName)).check(matches(isDisplayed()))
    }
}

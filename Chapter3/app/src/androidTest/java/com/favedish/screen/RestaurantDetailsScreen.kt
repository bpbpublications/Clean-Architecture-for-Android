package com.favedish.screen

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText

class RestaurantDetailsScreen {
    private val recyclerView =
        isAssignableFrom(RecyclerView::class.java)

    fun seeDishes(vararg titles: String) {
        titles.forEach { title ->
            onView(recyclerView).perform(
                actionOnItem<ViewHolder>(
                    hasDescendant(withText(title)),
                    scrollTo()
                )
            )
        }
    }

    fun tapOnDish(title: String) {
        onView(recyclerView).perform(
            actionOnItem<ViewHolder>(
                hasDescendant(withText(title)),
                scrollTo()
            ),
            actionOnItem<ViewHolder>(
                hasDescendant(withText(title)),
                click()
            )
        )
    }
}

package com.favedish.testing.action

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matcher

class NoAction : ViewAction {
    override fun getConstraints(): Matcher<View> = any(View::class.java)

    override fun getDescription(): String = "Performs no action."

    override fun perform(uiController: UiController?, view: View?) = Unit
}

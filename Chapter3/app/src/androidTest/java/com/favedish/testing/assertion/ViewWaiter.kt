package com.favedish.testing.assertion

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResourceTimeoutException
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.favedish.testing.action.NoAction
import com.favedish.testing.idlingresource.ViewIdlingResource
import org.hamcrest.Matcher
import org.junit.Assert.fail

fun waitForViews(timeoutMilliseconds: Long = 60L, vararg viewMatchers: Matcher<View>) {
    val viewIdlingResource =
        ViewIdlingResource(viewMatchers = viewMatchers, timeoutMilliseconds = timeoutMilliseconds)
    val idlingRegistry = IdlingRegistry.getInstance()
    idlingRegistry.register(viewIdlingResource)

    try {
        onView(isRoot()).perform(NoAction())
    } catch (timeout: IdlingResourceTimeoutException) {
        fail("Waiting for views timed out.")
    } finally {
        idlingRegistry.unregister(viewIdlingResource)
    }
}

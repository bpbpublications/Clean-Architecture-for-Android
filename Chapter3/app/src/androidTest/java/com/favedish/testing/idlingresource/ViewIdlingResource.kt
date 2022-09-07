package com.favedish.testing.idlingresource

import android.app.Activity
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.util.TreeIterables
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

class ViewIdlingResource(
    timeoutMilliseconds: Long = 60L,
    private vararg val viewMatchers: Matcher<View>
) : IdlingResource {
    private var resourceCallback: ResourceCallback? = null

    private val componentFinder by lazy {
        ComponentFinder()
    }

    init {
        IdlingPolicies.setIdlingResourceTimeout(timeoutMilliseconds, TimeUnit.MILLISECONDS)
    }

    override fun getName(): String = "ViewIdlingMatcher " + viewMatchers.joinToString(", ")

    override fun isIdleNow(): Boolean {
        val view = viewMatchers.asSequence().map { viewMatcher ->
            componentFinder.findMatchingView(viewMatcher)
        }.firstOrNull()

        val isIdle = view != null

        if (isIdle) {
            resourceCallback?.onTransitionToIdle()
        }

        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        resourceCallback = callback
    }
}

private class ComponentFinder {
    fun findMatchingView(matcher: Matcher<View>): View? {
        val activity = findCurrentActivity()
        activity?.findViewById<View>(android.R.id.content)?.let { activityView ->
            matchView(activityView, matcher)?.let { view ->
                return view
            }
        }

        if (activity !is AppCompatActivity) {
            return null
        }

        val matchedViews = mutableSetOf<View>()
        val fragmentViewMatcher = object : BaseMatcher<Fragment>() {
            override fun describeTo(description: Description) {
                description.appendText("Fragment matcher that matches internal fragment views")
            }

            override fun matches(item: Any?): Boolean {
                if (item !is Fragment) {
                    return false
                }

                val fragmentView = item.view ?: if (item is DialogFragment) {
                    item.dialog?.findViewById<View>(android.R.id.content)
                } else {
                    null
                } ?: let {
                    return false
                }

                matchView(fragmentView, matcher)?.let { view ->
                    matchedViews.add(view)
                    return true
                }

                return false
            }
        }

        return if (findMatchingFragment(fragmentViewMatcher) != null && matchedViews.isNotEmpty()) {
            matchedViews.first()
        } else {
            null
        }
    }

    fun findCurrentActivity(): Activity? = findAllActivities(Stage.RESUMED).firstOrNull()

    fun findMatchingFragment(matcher: Matcher<Fragment>): Fragment? {
        val activity = findCurrentActivity()
        if (activity !is AppCompatActivity) {
            return null
        }

        return activity.supportFragmentManager.fragments.let { fragments ->
            matchFragments(fragments, matcher)
        }
    }

    private fun matchView(view: View, matcher: Matcher<View>): View? {
        if (matcher.matches(view)) {
            return view
        }

        return TreeIterables.breadthFirstViewTraversal(view).firstOrNull(matcher::matches)
    }

    private fun matchFragments(fragments: List<Fragment>, matcher: Matcher<Fragment>): Fragment? {
        fragments.forEach { fragment ->
            if (matcher.matches(fragment)) {
                return fragment
            }
            fragment.childFragmentManager.fragments.forEach { childFragment ->
                if (matcher.matches(childFragment)) {
                    return childFragment
                }
            }
        }
        return null
    }

    private fun findAllActivities(activityStage: Stage): Set<Activity> {
        val foundActivities = mutableSetOf<Activity>()
        val findTask = Runnable {
            foundActivities.addAll(
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(activityStage)
            )
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            findTask.run()
        } else {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(findTask)
        }

        return foundActivities
    }
}

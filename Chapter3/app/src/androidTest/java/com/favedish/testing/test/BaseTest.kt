package com.favedish.testing.test

import android.app.Activity
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.favedish.R
import com.favedish.test.HiltTestActivity
import com.favedish.testing.rule.DisableAnimationsRule
import com.favedish.testing.rule.HiltInjectorRule
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain
import kotlin.reflect.KClass

abstract class BaseTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    private val hiltAndroidRule by lazy { HiltAndroidRule(this) }

    @get:Rule
    open val testRules: RuleChain by lazy {
        RuleChain.outerRule(hiltAndroidRule)
            .around(DisableAnimationsRule(UiDevice.getInstance(instrumentation)))
            .around(HiltInjectorRule(hiltAndroidRule))
    }

    protected abstract val testLauncher: TestLauncher<out Activity>

    @Before
    @CallSuper
    open fun setUp() {
        testLauncher.launch()
    }

    protected sealed class TestLauncher<ACTIVITY : Activity> {
        abstract fun launch(): ActivityScenario<out ACTIVITY>

        data class FromClass<ACTIVITY : Activity>(
            private val activityClass: KClass<out ACTIVITY>
        ) : TestLauncher<ACTIVITY>() {
            override fun launch(): ActivityScenario<out ACTIVITY> =
                ActivityScenario.launch(activityClass.java)
                    as ActivityScenario<out ACTIVITY>
        }

        data class WithFragment(
            private val fragmentClass: KClass<out Fragment>,
            private val fragmentArguments: Bundle = bundleOf()
        ) : TestLauncher<HiltTestActivity>() {
            override fun launch(): ActivityScenario<HiltTestActivity> =
                ActivityScenario.launch(HiltTestActivity::class.java)
                    .onActivity { activity ->
                        with(activity.supportFragmentManager) {
                            val fragment = fragmentFactory.instantiate(
                                checkNotNull(fragmentClass.java.classLoader),
                                fragmentClass.java.name
                            ).apply {
                                arguments = fragmentArguments
                            }
                            commitNow {
                                replace(R.id.main_fragment_container, fragment)
                            }
                        }
                    }
        }
    }
}

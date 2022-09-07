package com.favedish.testing.rule

import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.IOException

class DisableAnimationsRule(
    private val device: UiDevice
) : TestRule {
    private val settings = setOf(
        "transition_animation_scale",
        "window_animation_scale",
        "animator_duration_scale"
    ).map(::Setting)

    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                saveAnimationsState()
                disableAnimations()
                try {
                    base.evaluate()
                } finally {
                    restoreAnimationsState()
                }
            }
        }

    @Throws(IOException::class)
    private fun saveAnimationsState() {
        settings.forEach { setting ->
            setting.value =
                device.executeShellCommand("settings get global ${setting.key}")
                    .orDefault().toFloat()
        }
    }

    @Throws(IOException::class)
    private fun disableAnimations() {
        settings.forEach { setting ->
            device.executeShellCommand("settings put global ${setting.key} 0")
        }
    }

    @Throws(IOException::class)
    private fun restoreAnimationsState() {
        settings.forEach { setting ->
            device.executeShellCommand("settings put global ${setting.key} ${setting.value}")
        }
    }

    private fun String.orDefault() = if (isEmpty() || trim() == "null") {
        "1"
    } else {
        this
    }

    private data class Setting(
        val key: String,
        var value: Float = 0f
    )
}

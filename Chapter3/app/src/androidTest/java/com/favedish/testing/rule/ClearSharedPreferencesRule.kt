package com.favedish.testing.rule

import android.content.SharedPreferences
import androidx.core.content.edit
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ClearSharedPreferencesRule(sharedPreferencesProvider: () -> SharedPreferences) : TestRule {
    private val sharedPreferences by lazy { sharedPreferencesProvider() }

    override fun apply(base: Statement, description: Description) = object : Statement() {
        override fun evaluate() {
            sharedPreferences.edit {
                clear()
            }

            base.evaluate()

            sharedPreferences.edit {
                clear()
            }
        }
    }
}

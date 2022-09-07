package com.favedish.navigation.model

import androidx.navigation.NavController
import com.favedish.ui.navigation.model.UiDestination
import dagger.Lazy

class BackUiDestination(
    private val lazyNavController: Lazy<NavController>
) : UiDestination {
    override fun navigate() {
        lazyNavController.get().popBackStack()
    }
}

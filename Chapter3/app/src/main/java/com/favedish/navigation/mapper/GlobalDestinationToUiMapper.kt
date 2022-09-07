package com.favedish.navigation.mapper

import androidx.navigation.NavController
import com.favedish.navigation.model.UnhandledDestinationException
import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.ui.navigation.mapper.DestinationPresentationToUiMapper
import com.favedish.ui.navigation.model.UiDestination
import dagger.Lazy

class GlobalDestinationToUiMapper(
    private val lazyNavController: Lazy<NavController>
) : DestinationPresentationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        PresentationDestination.Back -> BackUiDestination(lazyNavController)
        else -> throw UnhandledDestinationException(input)
    }

    private class BackUiDestination(
        private val lazyNavController: Lazy<NavController>
    ) : UiDestination {
        override fun navigate() {
            lazyNavController.get().popBackStack()
        }
    }
}

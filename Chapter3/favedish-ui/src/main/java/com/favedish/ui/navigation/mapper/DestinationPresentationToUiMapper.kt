package com.favedish.ui.navigation.mapper

import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.ui.navigation.model.UiDestination

interface DestinationPresentationToUiMapper {
    fun toUi(input: PresentationDestination): UiDestination
}

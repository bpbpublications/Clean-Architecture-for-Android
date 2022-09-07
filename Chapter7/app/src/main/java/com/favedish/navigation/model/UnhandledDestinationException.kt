package com.favedish.navigation.model

import com.favedish.presentation.architecture.model.PresentationDestination

class UnhandledDestinationException(
    destination: PresentationDestination
) : IllegalArgumentException(
    "Navigation to ${destination::class.simpleName} was not handled."
)

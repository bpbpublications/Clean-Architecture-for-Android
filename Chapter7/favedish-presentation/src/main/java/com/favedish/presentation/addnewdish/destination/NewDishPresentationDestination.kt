package com.favedish.presentation.addnewdish.destination

import com.favedish.presentation.architecture.model.PresentationDestination

sealed interface NewDishPresentationDestination : PresentationDestination {
    data class NewDishCreationSuccess(
        val dishId: String
    ) : NewDishPresentationDestination
}

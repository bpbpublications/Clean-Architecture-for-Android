package com.favedish.app.navigation

import com.favedish.navigation.mapper.GlobalDestinationToUiMapper
import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.ui.dishdetails.mapper.DishDetailsDestinationToUiMapper
import com.favedish.ui.dishdetails.mapper.DishDetailsDestinationToUiMapper.DishDetailsUiDestination
import com.favedish.ui.navigation.model.UiDestination

class AppDishDetailsDestinationToUiMapper(
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : DishDetailsDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        else -> globalDestinationToUiMapper.toUi(input)
    }

    object GoNowhere : DishDetailsUiDestination {
        override fun navigate() = Unit
    }
}

package com.favedish.ui.addnewdish.mapper

import com.favedish.ui.navigation.mapper.DestinationPresentationToUiMapper
import com.favedish.ui.navigation.model.UiDestination

interface NewDishDestinationToUiMapper : DestinationPresentationToUiMapper {
    abstract class NewDishSuccessUiDestination(
        open val dishId: String
    ) : UiDestination
}

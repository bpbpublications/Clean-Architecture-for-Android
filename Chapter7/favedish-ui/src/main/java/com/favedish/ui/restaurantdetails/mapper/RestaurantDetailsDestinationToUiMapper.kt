package com.favedish.ui.restaurantdetails.mapper

import com.favedish.ui.navigation.mapper.DestinationPresentationToUiMapper
import com.favedish.ui.navigation.model.UiDestination

interface RestaurantDetailsDestinationToUiMapper : DestinationPresentationToUiMapper {
    abstract class DishDetailsUiDestination(
        open val dishId: String
    ) : UiDestination

    abstract class AddNewDishUiDestination(
        open val restaurantId: String
    ) : UiDestination
}

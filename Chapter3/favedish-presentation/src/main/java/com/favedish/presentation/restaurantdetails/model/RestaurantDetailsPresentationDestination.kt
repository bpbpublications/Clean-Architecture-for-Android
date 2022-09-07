package com.favedish.presentation.restaurantdetails.model

import com.favedish.presentation.architecture.model.PresentationDestination

sealed interface RestaurantDetailsPresentationDestination : PresentationDestination {
    data class DishDetails(
        val dishId: String
    ) : RestaurantDetailsPresentationDestination
}

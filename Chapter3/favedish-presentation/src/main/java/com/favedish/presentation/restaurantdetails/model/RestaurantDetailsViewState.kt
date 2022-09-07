package com.favedish.presentation.restaurantdetails.model

data class RestaurantDetailsViewState(
    val isLoading: Boolean = false,
    val dishes: Collection<DishPresentationModel> = emptyList()
) {
    fun loading(): RestaurantDetailsViewState = copy(isLoading = true)

    fun withDishes(dishes: Collection<DishPresentationModel>) = copy(
        isLoading = false,
        dishes = dishes
    )
}

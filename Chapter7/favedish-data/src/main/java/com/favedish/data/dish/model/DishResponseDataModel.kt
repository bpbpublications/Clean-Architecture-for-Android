package com.favedish.data.dish.model

data class DishResponseDataModel(
    val id: String,
    val name: String,
    val comment: String,
    val rating: Int,
    val restaurant: RestaurantDataModel
)

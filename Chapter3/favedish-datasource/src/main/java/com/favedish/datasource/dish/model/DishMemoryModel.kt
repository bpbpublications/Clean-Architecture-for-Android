package com.favedish.datasource.dish.model

data class DishMemoryModel(
    val id: String,
    val name: String,
    val comment: String,
    val rating: Int,
    val restaurant: RestaurantMemoryModel
)

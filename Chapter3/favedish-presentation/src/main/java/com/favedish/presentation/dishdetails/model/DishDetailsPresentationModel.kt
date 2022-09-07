package com.favedish.presentation.dishdetails.model

import com.favedish.presentation.dishdetails.mapper.RestaurantPresentationModel

data class DishDetailsPresentationModel(
    val id: String,
    val name: String,
    val comment: String,
    val rating: Int,
    val restaurant: RestaurantPresentationModel
)

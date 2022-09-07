package com.favedish.domain.dishdetails.model

data class DishDomainModel(
    val id: String,
    val name: String,
    val comment: String,
    val rating: Int,
    val restaurant: RestaurantDomainModel
)

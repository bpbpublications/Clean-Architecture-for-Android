package com.favedish.domain.restaurantdetails.repository

import com.favedish.domain.restaurantdetails.model.DishDomainModel

interface RestaurantDetailsRepository {
    fun dishes(restaurantId: String): Collection<DishDomainModel>
}

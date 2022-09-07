package com.favedish.domain.restaurantdetails.repository

import com.favedish.domain.restaurantdetails.model.DishDomainModel

class RestaurantDetailsDummyRepository : RestaurantDetailsRepository {
    override fun dishes(restaurantId: String) = listOf(
        DishDomainModel("#123", "Eggs Florentine"),
        DishDomainModel("#124", "Couscous")
    )
}

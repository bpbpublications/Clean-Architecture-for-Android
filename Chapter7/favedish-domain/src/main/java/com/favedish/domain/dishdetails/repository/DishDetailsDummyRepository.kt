package com.favedish.domain.dishdetails.repository

import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel

class DishDetailsDummyRepository : DishDetailsRepository {
    override fun dishDetails(dishId: String) = DishDomainModel(
        id = "#43",
        name = "Sweet Potatoes",
        comment = "Too salty.",
        rating = 4,
        restaurant = RestaurantDomainModel("")
    )
}

package com.favedish.presentation.dishdetails.mapper

import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.presentation.dishdetails.model.DishDetailsPresentationModel

class DishDetailsDomainToPresentationMapper(
    private val restaurantDomainToPresentationMapper:
        RestaurantDomainToPresentationMapper
) {
    fun toPresentation(input: DishDomainModel) =
        DishDetailsPresentationModel(
            id = input.id,
            name = input.name,
            comment = input.comment,
            rating = input.rating,
            restaurant = restaurantDomainToPresentationMapper
                .toPresentation(input.restaurant)
        )
}

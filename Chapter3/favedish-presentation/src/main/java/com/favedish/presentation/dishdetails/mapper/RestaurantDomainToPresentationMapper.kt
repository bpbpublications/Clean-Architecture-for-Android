package com.favedish.presentation.dishdetails.mapper

import com.favedish.domain.dishdetails.model.RestaurantDomainModel

class RestaurantDomainToPresentationMapper {
    fun toPresentation(input: RestaurantDomainModel) =
        RestaurantPresentationModel(input.name)
}

package com.favedish.presentation.restaurantdetails.mapper

import com.favedish.domain.restaurantdetails.model.DishDomainModel
import com.favedish.presentation.restaurantdetails.model.DishPresentationModel

class DishDomainToPresentationMapper {
    fun toPresentation(domainDish: DishDomainModel) = DishPresentationModel(
        id = domainDish.id,
        name = domainDish.name
    )
}

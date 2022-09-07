package com.favedish.presentation.addnewdish.mapper

import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.presentation.addnewdish.model.NewDishPresentationModel

class NewDishPresentationToDomainMapper {
    fun toDomain(newDish: NewDishPresentationModel) =
        NewDishDomainModel(
            name = newDish.name,
            restaurantId = newDish.restaurantId
        )
}

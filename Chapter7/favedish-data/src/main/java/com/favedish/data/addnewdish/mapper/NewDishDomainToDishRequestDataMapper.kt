package com.favedish.data.addnewdish.mapper

import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.domain.addnewdish.model.NewDishDomainModel

class NewDishDomainToDishRequestDataMapper {
    fun toData(newDish: NewDishDomainModel) = DishRequestDataModel(
        name = newDish.name,
        restaurantId = newDish.restaurantId
    )
}

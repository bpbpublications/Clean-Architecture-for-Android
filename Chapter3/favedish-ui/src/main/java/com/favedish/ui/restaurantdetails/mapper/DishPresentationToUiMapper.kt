package com.favedish.ui.restaurantdetails.mapper

import com.favedish.presentation.restaurantdetails.model.DishPresentationModel
import com.favedish.ui.restaurantdetails.model.DishUiModel

class DishPresentationToUiMapper {
    fun toUi(dish: DishPresentationModel) = DishUiModel(
        id = dish.id,
        name = dish.name
    )
}

package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.datasource.dish.model.DishMemoryModel

class DishMemoryToResponseDataMapper(
    private val restaurantMemoryToDataMapper: RestaurantMemoryToDataMapper
) {
    fun toData(input: DishMemoryModel) = DishResponseDataModel(
        input.id,
        input.name,
        input.comment,
        input.rating,
        restaurantMemoryToDataMapper.toData(input.restaurant)
    )
}

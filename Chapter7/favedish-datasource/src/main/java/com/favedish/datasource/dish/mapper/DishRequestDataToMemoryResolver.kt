package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.model.DishMemoryModel

private const val DEFAULT_COMMENT = ""
private const val DEFAULT_RATING = 0

class DishRequestDataToMemoryResolver(
    private val restaurantDataToMemoryMapper: RestaurantDataToMemoryMapper
) {
    fun toMemory(
        dish: DishRequestDataModel,
        dishId: String,
        restaurant: RestaurantDataModel
    ) = DishMemoryModel(
        dishId,
        dish.name,
        DEFAULT_COMMENT,
        DEFAULT_RATING,
        restaurantDataToMemoryMapper.toMemory(restaurant)
    )
}

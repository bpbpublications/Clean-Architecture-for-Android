package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel

class RestaurantDataToMemoryMapper {
    fun toMemory(input: RestaurantDataModel) = RestaurantMemoryModel(input.name)
}

package com.favedish.datasource.dish.mapper

import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.datasource.dish.model.RestaurantMemoryModel

class RestaurantMemoryToDataMapper {
    fun toData(input: RestaurantMemoryModel) = RestaurantDataModel(input.name)
}

package com.favedish.datasource.dish.datasource

import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.datasource.dish.mapper.DishMemoryToResponseDataMapper
import com.favedish.datasource.dish.model.DishMemoryModel

class DishLiveDataSource(
    private val dishStore: MutableMap<String, DishMemoryModel>,
    private val dishMemoryToResponseDataMapper: DishMemoryToResponseDataMapper
) : DishDataSource {
    override fun dishDetails(dishId: String): DishResponseDataModel {
        val dish = requireNotNull(
            dishStore[dishId]
        ) { "Dish $dishId not found" }
        return dishMemoryToResponseDataMapper.toData(dish)
    }
}

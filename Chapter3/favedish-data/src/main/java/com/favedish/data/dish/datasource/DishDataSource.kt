package com.favedish.data.dish.datasource

import com.favedish.data.dish.model.DishResponseDataModel

interface DishDataSource {
    fun dishDetails(dishId: String): DishResponseDataModel
}

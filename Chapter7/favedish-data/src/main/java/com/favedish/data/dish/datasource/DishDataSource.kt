package com.favedish.data.dish.datasource

import com.favedish.data.dish.model.DishRequestDataModel
import com.favedish.data.dish.model.DishResponseDataModel

interface DishDataSource {
    fun addNewDish(newDish: DishRequestDataModel): String
    fun dishDetails(dishId: String): DishResponseDataModel
}

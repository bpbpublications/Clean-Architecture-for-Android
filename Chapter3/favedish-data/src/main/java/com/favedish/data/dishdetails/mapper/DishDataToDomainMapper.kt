package com.favedish.data.dishdetails.mapper

import com.favedish.data.dish.model.DishResponseDataModel
import com.favedish.domain.dishdetails.model.DishDomainModel

class DishDataToDomainMapper(
    private val restaurantDataToDomainMapper: RestaurantDataToDomainMapper
) {
    fun toDomain(input: DishResponseDataModel) =
        DishDomainModel(
            input.id,
            input.name,
            input.comment,
            input.rating,
            restaurantDataToDomainMapper.toDomain(input.restaurant)
        )
}

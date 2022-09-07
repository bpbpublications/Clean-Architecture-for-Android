package com.favedish.data.dishdetails.mapper

import com.favedish.data.dish.model.RestaurantDataModel
import com.favedish.domain.dishdetails.model.RestaurantDomainModel

class RestaurantDataToDomainMapper {
    fun toDomain(input: RestaurantDataModel) = RestaurantDomainModel(input.name)
}

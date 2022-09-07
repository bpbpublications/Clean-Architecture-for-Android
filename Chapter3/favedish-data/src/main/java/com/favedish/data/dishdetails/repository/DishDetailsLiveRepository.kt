package com.favedish.data.dishdetails.repository

import com.favedish.data.dish.datasource.DishDataSource
import com.favedish.data.dishdetails.mapper.DishDataToDomainMapper
import com.favedish.domain.dishdetails.repository.DishDetailsRepository

class DishDetailsLiveRepository(
    private val dishDataSource: DishDataSource,
    private val dishDataToDomainMapper: DishDataToDomainMapper
) : DishDetailsRepository {
    override fun dishDetails(dishId: String) =
        dishDataToDomainMapper.toDomain(
            dishDataSource.dishDetails(dishId)
        )
}

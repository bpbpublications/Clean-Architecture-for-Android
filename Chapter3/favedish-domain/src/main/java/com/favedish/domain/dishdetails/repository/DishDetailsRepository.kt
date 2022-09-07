package com.favedish.domain.dishdetails.repository

import com.favedish.domain.dishdetails.model.DishDomainModel

interface DishDetailsRepository {
    fun dishDetails(dishId: String): DishDomainModel
}

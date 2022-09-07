package com.favedish.domain.restaurantdetails.usecase

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import com.favedish.domain.restaurantdetails.model.DishDomainModel
import com.favedish.domain.restaurantdetails.repository.RestaurantDetailsRepository

class GetDishesUseCase(
    private val restaurantDetailsRepository: RestaurantDetailsRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, Collection<DishDomainModel>>(coroutineContextProvider) {
    override fun executeInBackground(
        request: String
    ) = restaurantDetailsRepository.dishes(request)
}

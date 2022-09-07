package com.favedish.domain.dishdetails.usecase

import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.repository.DishDetailsRepository

class GetDishDetailsUseCase(
    private val dishDetailsRepository: DishDetailsRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, DishDomainModel>(coroutineContextProvider) {
    override fun executeInBackground(
        request: String
    ) = dishDetailsRepository.dishDetails(request)
}

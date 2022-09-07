package com.favedish.domain.addnewdish.usecase

import com.favedish.domain.addnewdish.model.NewDishDomainModel
import com.favedish.domain.addnewdish.repository.AddNewDishRepository
import com.favedish.domain.cleanarchitecture.coroutine.CoroutineContextProvider
import com.favedish.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase

class AddNewDishUseCase(
    private val addNewDishRepository: AddNewDishRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewDishDomainModel, String>(coroutineContextProvider) {
    override fun executeInBackground(
        request: NewDishDomainModel
    ) = addNewDishRepository.addNewDish(request)
}

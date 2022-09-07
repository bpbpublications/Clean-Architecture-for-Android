package com.favedish.presentation.addnewdish.viewmodel

import com.favedish.domain.addnewdish.usecase.AddNewDishUseCase
import com.favedish.presentation.addnewdish.destination.NewDishPresentationDestination.NewDishCreationSuccess
import com.favedish.presentation.addnewdish.mapper.NewDishPresentationToDomainMapper
import com.favedish.presentation.addnewdish.model.NewDishPresentationModel
import com.favedish.presentation.addnewdish.model.NewDishPresentationNotification
import com.favedish.presentation.addnewdish.model.NewDishViewState
import com.favedish.presentation.architecture.viewmodel.BaseViewModel
import com.favedish.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewDishViewModel @Inject constructor(
    private val addNewDishUseCase: AddNewDishUseCase,
    private val newDishPresentationToDomainMapper:
        NewDishPresentationToDomainMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<NewDishViewState, NewDishPresentationNotification>(
    useCaseExecutorProvider
) {
    override fun initialState() = NewDishViewState()

    fun onAddNewDishAction(dish: NewDishPresentationModel) {
        updateViewState(NewDishViewState::loading)
        addNewDish(dish)
    }

    private fun addNewDish(newDish: NewDishPresentationModel) {
        val domainNewDish = newDishPresentationToDomainMapper
            .toDomain(newDish)
        execute(addNewDishUseCase, domainNewDish, ::handleNewDishResult)
    }

    private fun handleNewDishResult(dishId: String) {
        updateViewState(NewDishViewState::idle)
        navigateTo(NewDishCreationSuccess(dishId))
    }
}

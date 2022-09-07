package com.favedish.presentation.dishdetails.viewmodel

import com.favedish.domain.dishdetails.model.DishDomainModel
import com.favedish.domain.dishdetails.usecase.GetDishDetailsUseCase
import com.favedish.presentation.architecture.viewmodel.BaseViewModel
import com.favedish.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import com.favedish.presentation.dishdetails.mapper.DishDetailsDomainToPresentationMapper
import com.favedish.presentation.dishdetails.model.DishDetailsPresentationNotification
import com.favedish.presentation.dishdetails.model.DishDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DishDetailsViewModel @Inject constructor(
    private val getDishDetailsUseCase: GetDishDetailsUseCase,
    private val dishDetailsDomainToPresentationMapper: DishDetailsDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<DishDetailsViewState, DishDetailsPresentationNotification>(
    useCaseExecutorProvider
) {
    override fun initialState() = DishDetailsViewState()

    fun onEntered(dishId: String) {
        updateViewState(DishDetailsViewState::loading)
        fetchDishDetails(dishId)
    }

    private fun fetchDishDetails(dishId: String) {
        execute(getDishDetailsUseCase, dishId, ::presentDishDetails)
    }

    private fun presentDishDetails(dish: DishDomainModel) {
        val dishDetails = dishDetailsDomainToPresentationMapper
            .toPresentation(dish)
        updateViewState { dishDetailsReady(dishDetails) }
    }
}

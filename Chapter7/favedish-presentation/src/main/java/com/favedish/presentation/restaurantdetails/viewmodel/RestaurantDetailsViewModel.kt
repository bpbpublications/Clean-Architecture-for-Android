package com.favedish.presentation.restaurantdetails.viewmodel

import com.favedish.domain.restaurantdetails.model.DishDomainModel
import com.favedish.domain.restaurantdetails.usecase.GetDishesUseCase
import com.favedish.presentation.architecture.viewmodel.BaseViewModel
import com.favedish.presentation.architecture.viewmodel.usecase.UseCaseExecutorProvider
import com.favedish.presentation.restaurantdetails.mapper.DishDomainToPresentationMapper
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsPresentationDestination.AddNewDish
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsPresentationDestination.DishDetails
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private typealias DoNothing = Unit

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    private val getDishesUseCase: GetDishesUseCase,
    private val dishToPresentationMapper: DishDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<RestaurantDetailsViewState, Unit>(useCaseExecutorProvider) {
    override fun initialState() = RestaurantDetailsViewState()

    fun onEntered(restaurantId: String) {
        updateViewState(RestaurantDetailsViewState::loading)
        execute(
            getDishesUseCase,
            restaurantId,
            onSuccess = { dishes -> presentDishes(dishes) },
            onException = { DoNothing }
        )
    }

    fun onDishDetailsAction(dishId: String) {
        navigateTo(DishDetails(dishId))
    }

    fun onAddNewDishAction(restaurantId: String) {
        navigateTo(AddNewDish(restaurantId))
    }

    private fun presentDishes(dishes: Collection<DishDomainModel>) {
        updateViewState {
            withDishes(dishes.map(dishToPresentationMapper::toPresentation))
        }
    }
}

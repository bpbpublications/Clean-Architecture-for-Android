package com.favedish.ui.restaurantdetails.binder

import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsViewState
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.restaurantdetails.adapter.DishesAdapter
import com.favedish.ui.restaurantdetails.mapper.DishPresentationToUiMapper
import com.favedish.ui.restaurantdetails.view.RestaurantDetailsViewsProvider

class RestaurantDetailsViewStateBinder(
    private val onDishClickListener: OnClickListener,
    private val dishToUiMapper: DishPresentationToUiMapper
) : ViewStateBinder<RestaurantDetailsViewState, RestaurantDetailsViewsProvider> {
    private val dishesAdapter by lazy {
        DishesAdapter().apply {
            onDishClickListener = _onDishClickListener
        }
    }

    private val _onDishClickListener: DishesAdapter.OnClickListener =
        DelegateOnClickListener()

    override fun RestaurantDetailsViewsProvider.bindState(viewState: RestaurantDetailsViewState) {
        if (dishesListView.adapter == null) {
            dishesListView.adapter = dishesAdapter
        }
        dishesAdapter.setDishes(viewState.dishes.map(dishToUiMapper::toUi))
    }

    private inner class DelegateOnClickListener : DishesAdapter.OnClickListener {
        override fun onDishClick(dishId: String) {
            onDishClickListener.onDishClick(dishId)
        }
    }

    interface OnClickListener {
        fun onDishClick(dishId: String)
    }
}

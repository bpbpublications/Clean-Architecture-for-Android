package com.favedish.app.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.favedish.R
import com.favedish.navigation.mapper.GlobalDestinationToUiMapper
import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsPresentationDestination.DishDetails
import com.favedish.ui.dishdetails.view.DishDetailsFragment
import com.favedish.ui.navigation.model.UiDestination
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsDestinationToUiMapper
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsDestinationToUiMapper.DishDetailsUiDestination

class AppRestaurantDetailsDestinationToUiMapper(
    private val fragmentManager: FragmentManager,
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : RestaurantDetailsDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        is DishDetails -> AppDishDetails(fragmentManager, input.dishId)
        else -> globalDestinationToUiMapper.toUi(input)
    }

    private data class AppDishDetails(
        private val fragmentManager: FragmentManager,
        override val dishId: String
    ) : DishDetailsUiDestination(dishId) {
        override fun navigate() {
            fragmentManager.commit {
                replace(
                    R.id.main_fragment_container,
                    DishDetailsFragment.newInstance(dishId)
                )
                addToBackStack("DishDetails[$dishId]")
            }
        }
    }
}

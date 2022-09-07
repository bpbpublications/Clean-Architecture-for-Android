package com.favedish.app.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.favedish.R
import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.ui.addnewdish.mapper.NewDishDestinationToUiMapper
import com.favedish.ui.addnewdish.mapper.NewDishDestinationToUiMapper.NewDishSuccessUiDestination
import com.favedish.ui.dishdetails.view.DishDetailsFragment
import com.favedish.navigation.mapper.GlobalDestinationToUiMapper
import com.favedish.presentation.addnewdish.destination.NewDishPresentationDestination.NewDishCreationSuccess
import com.favedish.ui.navigation.model.UiDestination

class AppNewDishDestinationToUiMapper(
    private val fragmentManager: FragmentManager,
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : NewDishDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        is NewDishCreationSuccess -> {
            AppDishDetails(fragmentManager, input.dishId)
        }
        else -> globalDestinationToUiMapper.toUi(input)
    }

    data class AppDishDetails(
        private val fragmentManager: FragmentManager,
        override val dishId: String
    ) : NewDishSuccessUiDestination(dishId) {
        override fun navigate() {
            fragmentManager.popBackStack()
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

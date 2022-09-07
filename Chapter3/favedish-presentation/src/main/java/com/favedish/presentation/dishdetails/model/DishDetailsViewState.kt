package com.favedish.presentation.dishdetails.model

data class DishDetailsViewState(
    val isLoading: Boolean = false,
    val dishDetails: DishDetailsPresentationModel? = null
) {
    fun loading() = copy(isLoading = true)

    fun dishDetailsReady(
        dishDetails: DishDetailsPresentationModel
    ) = copy(dishDetails = dishDetails, isLoading = false)
}

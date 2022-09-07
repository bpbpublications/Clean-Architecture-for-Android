package com.favedish.ui.dishdetails.binder

import android.content.res.Resources
import com.favedish.presentation.dishdetails.model.DishDetailsViewState
import com.favedish.ui.R
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.dishdetails.view.DishDetailsViewsProvider

class DishDetailsViewStateBinder(
    private val resources: Resources
) : ViewStateBinder<DishDetailsViewState, DishDetailsViewsProvider> {
    override fun DishDetailsViewsProvider.bindState(
        viewState: DishDetailsViewState
    ) {
        dishNameField.text = viewState.dishDetails?.name
            ?: resources.getString(R.string.dish_loading)
    }
}

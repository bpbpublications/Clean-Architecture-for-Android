package com.favedish.ui.addnewdish.binder

import com.favedish.presentation.addnewdish.model.NewDishViewState
import com.favedish.ui.addnewdish.view.NewDishViewsProvider
import com.favedish.ui.architecture.mapper.ViewStateBinder

class NewDishViewStateBinder :
    ViewStateBinder<NewDishViewState, NewDishViewsProvider> {
    override fun NewDishViewsProvider
        .bindState(viewState: NewDishViewState) = Unit
}

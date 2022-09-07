package com.favedish.test.viewmodel

import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.presentation.architecture.viewmodel.BaseViewModel

fun <VIEW_STATE : Any, NOTIFICATION : Any> BaseViewModel<VIEW_STATE, NOTIFICATION>.observeViewStateForever() =
    mutableListOf<VIEW_STATE>().apply {
        viewState.observeForever(::add)
    }

fun <VIEW_STATE : Any, NOTIFICATION : Any> BaseViewModel<VIEW_STATE, NOTIFICATION>.observeDestinationForever() =
    mutableListOf<PresentationDestination>().apply {
        destination.observeForever(::add)
    }

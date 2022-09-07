package com.favedish.presentation.addnewdish.model

data class NewDishViewState(
    val isLoading: Boolean = false
) {
    fun loading() = copy(isLoading = true)

    fun idle() = copy(isLoading = false)
}

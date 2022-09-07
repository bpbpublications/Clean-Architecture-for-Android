package com.favedish.ui.addnewdish.view

import android.view.View
import android.widget.TextView
import com.favedish.ui.architecture.view.ViewsProvider

interface NewDishViewsProvider : ViewsProvider {
    val dishNameInput: TextView
    val submitButton: View
}

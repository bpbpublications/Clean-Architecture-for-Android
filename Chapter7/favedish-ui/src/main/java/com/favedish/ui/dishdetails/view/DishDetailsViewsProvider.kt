package com.favedish.ui.dishdetails.view

import android.widget.TextView
import com.favedish.ui.architecture.view.ViewsProvider

interface DishDetailsViewsProvider : ViewsProvider {
    val dishNameField: TextView
}

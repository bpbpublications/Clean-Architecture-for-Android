package com.favedish.ui.restaurantdetails.view

import androidx.recyclerview.widget.RecyclerView
import com.favedish.ui.architecture.view.ViewsProvider

interface RestaurantDetailsViewsProvider : ViewsProvider {
    val dishesListView: RecyclerView
}

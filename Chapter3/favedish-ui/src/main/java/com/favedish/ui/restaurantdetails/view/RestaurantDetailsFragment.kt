package com.favedish.ui.restaurantdetails.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.favedish.presentation.restaurantdetails.model.RestaurantDetailsViewState
import com.favedish.presentation.restaurantdetails.viewmodel.RestaurantDetailsViewModel
import com.favedish.ui.R
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.BaseFragment
import com.favedish.ui.architecture.view.ViewsProvider
import com.favedish.ui.restaurantdetails.binder.RestaurantDetailsViewStateBinder
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsDestinationToUiMapper
import com.favedish.ui.restaurantdetails.mapper.RestaurantDetailsNotificationPresentationToUiMapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantDetailsFragment :
    BaseFragment<RestaurantDetailsViewState, Unit>(),
    RestaurantDetailsViewsProvider,
    RestaurantDetailsViewStateBinder.OnClickListener {
    override val viewModel: RestaurantDetailsViewModel by viewModels()

    @Inject
    override lateinit var destinationMapper: RestaurantDetailsDestinationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder: ViewStateBinder<RestaurantDetailsViewState, ViewsProvider>

    @Inject
    override lateinit var notificationMapper: RestaurantDetailsNotificationPresentationToUiMapper

    override val layoutResourceId = R.layout.fragment_restaurant_details

    override lateinit var dishesListView: RecyclerView

    private val restaurantId by lazy {
        requireNotNull(
            requireArguments().getString(ARGUMENT_RESTAURANT_ID, null)
        ) { "A restaurant ID must be provided." }
    }

    override fun View.bindViews() {
        dishesListView = findViewById(R.id.restaurant_details_dishes_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.onEntered(restaurantId)
        }
    }

    override fun onDishClick(dishId: String) {
        viewModel.onDishDetailsAction(dishId)
    }

    companion object {
        private const val ARGUMENT_RESTAURANT_ID = "RestaurantId"

        fun newInstance(restaurantId: String) = RestaurantDetailsFragment().apply {
            arguments = bundleOf(ARGUMENT_RESTAURANT_ID to restaurantId)
        }
    }
}

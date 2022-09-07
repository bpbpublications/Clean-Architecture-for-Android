package com.favedish.ui.dishdetails.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.favedish.presentation.dishdetails.model.DishDetailsPresentationNotification
import com.favedish.presentation.dishdetails.model.DishDetailsViewState
import com.favedish.presentation.dishdetails.viewmodel.DishDetailsViewModel
import com.favedish.ui.R
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.BaseFragment
import com.favedish.ui.architecture.view.ViewsProvider
import com.favedish.ui.dishdetails.mapper.DishDetailsDestinationToUiMapper
import com.favedish.ui.dishdetails.mapper.DishDetailsNotificationPresentationToUiMapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DishDetailsFragment :
    BaseFragment<DishDetailsViewState, DishDetailsPresentationNotification>(),
    DishDetailsViewsProvider {
    override val viewModel: DishDetailsViewModel by viewModels()

    @Inject
    override lateinit var destinationMapper:
        DishDetailsDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
        DishDetailsNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
        ViewStateBinder<DishDetailsViewState, ViewsProvider>

    override val layoutResourceId = R.layout.fragment_dish_details

    private val dishId by lazy {
        requireNotNull(
            requireArguments().getString(ARGUMENT_DISH_ID, null)
        ) { "A dish ID must be provided." }
    }

    override lateinit var dishNameField: TextView

    override fun View.bindViews() {
        dishNameField = findViewById(R.id.dish_details_name_field)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.onEntered(dishId)
        }
    }

    companion object {
        private const val ARGUMENT_DISH_ID = "DishId"

        fun newInstance(dishId: String) = DishDetailsFragment().apply {
            arguments = bundleOf(ARGUMENT_DISH_ID to dishId)
        }
    }
}

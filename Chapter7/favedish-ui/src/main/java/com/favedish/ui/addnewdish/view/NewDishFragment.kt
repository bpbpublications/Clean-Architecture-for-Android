package com.favedish.ui.addnewdish.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.favedish.presentation.addnewdish.model.NewDishPresentationModel
import com.favedish.presentation.addnewdish.model.NewDishPresentationNotification
import com.favedish.presentation.addnewdish.model.NewDishViewState
import com.favedish.presentation.addnewdish.viewmodel.NewDishViewModel
import com.favedish.ui.R
import com.favedish.ui.addnewdish.mapper.NewDishDestinationToUiMapper
import com.favedish.ui.addnewdish.mapper.NewDishNotificationPresentationToUiMapper
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.BaseFragment
import com.favedish.ui.architecture.view.ViewsProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewDishFragment :
    BaseFragment<NewDishViewState, NewDishPresentationNotification>(),
    NewDishViewsProvider {
    override val viewModel: NewDishViewModel by viewModels()

    @Inject
    override lateinit var destinationMapper:
        NewDishDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
        NewDishNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
        ViewStateBinder<NewDishViewState, ViewsProvider>

    override val layoutResourceId = R.layout.fragment_new_dish

    private val restaurantId by lazy {
        requireNotNull(
            requireArguments().getString(ARGUMENT_RESTAURANT_ID, null)
        ) { "A restaurant ID must be provided." }
    }

    override lateinit var dishNameInput: EditText

    override lateinit var submitButton: View

    override fun View.bindViews() {
        dishNameInput = findViewById(R.id.new_dish_name_field)
        submitButton = findViewById(R.id.new_dish_submit_button)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        submitButton.setOnClickListener {
            viewModel.onAddNewDishAction(
                NewDishPresentationModel(
                    restaurantId = restaurantId,
                    name = dishNameInput.text.toString()
                )
            )
        }
        return view
    }

    companion object {
        private const val ARGUMENT_RESTAURANT_ID = "RestaurantId"

        fun newInstance(restaurantId: String) =
            NewDishFragment().apply {
                arguments = bundleOf(ARGUMENT_RESTAURANT_ID to restaurantId)
            }
    }
}

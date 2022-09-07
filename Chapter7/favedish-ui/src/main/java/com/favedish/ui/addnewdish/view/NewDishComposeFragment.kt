package com.favedish.ui.addnewdish.view

//import com.favedish.ui.addnewdish.view.widget.CallToActionButton
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnLifecycleDestroyed
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
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
class NewDishComposeFragment :
    BaseFragment<NewDishViewState, NewDishPresentationNotification>() {
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

    private val restaurantId by lazy {
        requireArguments().getString(ARGUMENT_RESTAURANT_ID, null)
            ?: throw IllegalArgumentException("A restaurant ID must be provided.")
    }

    override fun View.bindViews() = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                ComposedContent()
            }
        }
    }

    @Composable
    private fun ComposedContent() =
        MaterialTheme {
            Column {
                var dishName by remember { mutableStateOf("") }
                TextField(
                    value = dishName,
                    onValueChange = { dishName = it },
                    label = {
                        Text(getString(R.string.new_dish_dish_name_hint))
                    }
                )
//                CallToActionButton(
//                    text = getString(R.string.new_dish_add_button_label),
//                    onClick = {
//                        viewModel.onAddNewDishAction(
//                            NewDishPresentationModel(
//                                restaurantId = restaurantId,
//                                name = dishName
//                            )
//                        )
//                    }
//                )
            }
        }

    companion object {
        private const val ARGUMENT_RESTAURANT_ID = "RestaurantId"

        fun newInstance(restaurantId: String) =
            NewDishComposeFragment().apply {
                arguments = bundleOf(ARGUMENT_RESTAURANT_ID to restaurantId)
            }
    }
}

package com.favedish.ui.architecture.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.favedish.presentation.architecture.model.PresentationDestination
import com.favedish.presentation.architecture.viewmodel.BaseViewModel
import com.favedish.ui.architecture.mapper.NotificationPresentationToUiMapper
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.navigation.mapper.DestinationPresentationToUiMapper

private const val NO_LAYOUT_RESOURCE = 0

abstract class BaseFragment<VIEW_STATE : Any, NOTIFICATION : Any> : Fragment(), ViewsProvider {
    protected abstract val viewModel: BaseViewModel<VIEW_STATE, NOTIFICATION>

    open val layoutResourceId: Int = NO_LAYOUT_RESOURCE

    abstract val destinationMapper: DestinationPresentationToUiMapper
    abstract val notificationMapper: NotificationPresentationToUiMapper<NOTIFICATION>
    abstract val viewStateBinder: ViewStateBinder<VIEW_STATE, ViewsProvider>

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = if (layoutResourceId != NO_LAYOUT_RESOURCE) {
            inflater.inflate(layoutResourceId, container, false).apply {
                bindViews()
            }
        } else {
            null
        }
        observeViewModel()
        return view
    }

    abstract fun View.bindViews()

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, ::applyViewState)
        viewModel.notification.observe(viewLifecycleOwner, ::showNotification)
        viewModel.destination.observe(viewLifecycleOwner, ::navigateToDestination)
    }

    private fun applyViewState(viewState: VIEW_STATE) {
        with(viewStateBinder) {
            bindState(viewState)
        }
    }

    private fun showNotification(notification: NOTIFICATION) {
        notificationMapper.toUi(notification).show()
    }

    private fun navigateToDestination(destination: PresentationDestination) {
        destinationMapper.toUi(destination).navigate()
    }
}

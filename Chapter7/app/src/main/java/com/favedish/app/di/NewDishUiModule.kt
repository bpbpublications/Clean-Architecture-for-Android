package com.favedish.app.di

import com.favedish.presentation.addnewdish.model.NewDishViewState
import com.favedish.ui.addnewdish.binder.NewDishViewStateBinder
import com.favedish.ui.addnewdish.mapper.NewDishNotificationPresentationToUiMapper
import com.favedish.ui.architecture.mapper.ViewStateBinder
import com.favedish.ui.architecture.view.ViewsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class NewDishUiModule {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesDishListViewStateBinder() = NewDishViewStateBinder()
        as ViewStateBinder<NewDishViewState, ViewsProvider>

    @Provides
    fun providesNewDishNotificationPresentationToUiMapper() =
        NewDishNotificationPresentationToUiMapper()
}

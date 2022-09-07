package com.favedish.ui.addnewdish.mapper

import com.favedish.presentation.addnewdish.model.NewDishPresentationNotification
import com.favedish.ui.architecture.mapper.NotificationPresentationToUiMapper
import com.favedish.ui.architecture.model.NotificationUiModel

class NewDishNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewDishPresentationNotification> {
    override fun toUi(
        presentationNotification: NewDishPresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}

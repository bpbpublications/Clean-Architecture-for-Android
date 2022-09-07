package com.favedish.ui.restaurantdetails.mapper

import com.favedish.ui.architecture.mapper.NotificationPresentationToUiMapper
import com.favedish.ui.architecture.model.NotificationUiModel

class RestaurantDetailsNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<Unit> {
    override fun toUi(presentationNotification: Unit): NotificationUiModel {
        TODO("No notifications implemented for the restaurant details screen.")
    }
}

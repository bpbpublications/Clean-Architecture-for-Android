package com.favedish.testing.server

import com.favedish.testing.server.MockResponseContents.ErrorResponse
import com.favedish.testing.server.MockResponseContents.SimpleResponse
import com.favedish.testing.server.RequestResponseKey.NO_DISHES
import com.favedish.testing.server.RequestResponseKey.TWO_DISHES

object RequestResponseKey {
    const val TWO_DISHES = "TwoDishes"
    const val NO_DISHES = "NoDishes"
}

class RequestResponseStore {
    val requestResponses = mapOf(
        TWO_DISHES to MockRequestResponse(
            request = MockRequest("dishes"),
            response = SimpleResponse(bodyFileName = "dishes.json")
        ),
        NO_DISHES to MockRequestResponse(
            request = MockRequest("dishes"),
            response = ErrorResponse.NotFound
        )
    )
}

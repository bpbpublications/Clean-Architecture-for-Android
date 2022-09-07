package com.favedish.testing.server

class ResponseContentStore {
    private val responses = mutableMapOf<MockRequest, MockResponseContents>()

    fun reset() {
        responses.clear()
    }

    fun addResponse(request: MockRequest, response: MockResponseContents) {
        responses[request] = response
    }

    fun response(request: MockRequest) = responses[request]
}

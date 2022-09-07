package com.favedish.testing.server

import okhttp3.Headers
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher(
    private val responseContentStore: ResponseContentStore,
    private val tokenStore: TokenStore
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        val mockRequest = MockRequest(request.path!!.substringBefore("?"))
        val response = responseContentStore
            .response(mockRequest)?.mockResponse(tokenStore)
            ?: ServerResponse(code = 404)
        return MockResponse().apply {
            headers = Headers.headersOf(*response.headers.toArray())
        }.setResponseCode(response.code)
            .setBody(response.body)
    }

    private fun Collection<Pair<String, String>>.toArray(): Array<String> =
        flatMap { listOf(it.first, it.second) }.toTypedArray()
}

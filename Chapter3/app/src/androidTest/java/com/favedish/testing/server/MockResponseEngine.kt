package com.favedish.testing.server

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

class MockResponseEngine(
    private val responseContentStore: ResponseContentStore,
    private val tokenStore: TokenStore
) {
    val mockEngine = MockEngine { request ->
        val requestUrl = request.url.fullPath.substringBefore("?")
        val mockRequest = MockRequest(requestUrl)
        val response = responseContentStore
            .response(mockRequest)?.mockResponse(tokenStore)
            ?: ServerResponse(code = 404)

        respond(
            status = HttpStatusCode(
                response.code,
                "Mocked ${responseContentStore.response(mockRequest) != null}"
            ),
            headers = headersOf(*response.headers.toArray()),
            content = ByteReadChannel(response.body)
        )
    }

    private fun Collection<Pair<String, String>>.toArray() =
        map { header -> Pair(header.first, listOf(header.second)) }
            .toTypedArray()
}

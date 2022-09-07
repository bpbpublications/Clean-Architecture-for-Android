package com.favedish.testing.server

sealed class MockResponseContents {
    abstract fun mockResponse(tokenStore: TokenStore): ServerResponse

    data class SimpleResponse(
        private val responseCode: Int = 200,
        private val bodyFileName: String? = null,
        private val headers: List<Pair<String, String>> = emptyList()
    ) : MockResponseContents() {
        override fun mockResponse(tokenStore: TokenStore) = ServerResponse(
            code = responseCode,
            headers = headers,
            body = responseBody(tokenStore.tokens)
        )

        private fun responseBody(
            tokens: Collection<Pair<String, () -> String>>
        ) = bodyFileName?.run {
            getAssetAsString(bodyFileName)
                .replaceTokens(tokens)
        }.orEmpty()
    }

    sealed class ErrorResponse {
        object NotFound : MockResponseContents() {
            override fun mockResponse(tokenStore: TokenStore) =
                ServerResponse(code = 404)
        }
    }
}

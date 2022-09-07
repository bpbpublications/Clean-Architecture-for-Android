package com.favedish.testing.server

data class ServerResponse(
    val code: Int = 200,
    val headers: List<Pair<String, String>> = emptyList(),
    val body: String = ""
)

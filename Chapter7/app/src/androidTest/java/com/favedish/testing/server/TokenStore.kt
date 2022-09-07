package com.favedish.testing.server

interface TokenStore {
    val tokens: Collection<Pair<String, () -> String>>
}

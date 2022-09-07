package com.favedish.testing.server

fun String.replaceTokens(
    tokens: Collection<Pair<String, () -> String>>
): String = tokens.fold(this) { accumulator, token ->
    accumulator.replace("<${token.first}>", token.second())
}

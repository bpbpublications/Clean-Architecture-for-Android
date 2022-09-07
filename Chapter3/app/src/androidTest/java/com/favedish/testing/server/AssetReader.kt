package com.favedish.testing.server

import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.stream.Collectors.joining

fun getAssetAsString(
    filename: String,
    delimiter: String = "\n"
): String = processAssetStream(filename) { inputStream ->
    BufferedReader(InputStreamReader(inputStream))
        .lines()
        .collect(joining(delimiter))
}

fun <OUTPUT> processAssetStream(
    filename: String,
    performOnStream: (inputString: InputStream) -> OUTPUT
): OUTPUT {
    val stream = InstrumentationRegistry.getInstrumentation()
        .context.assets.open(filename)
    val result = performOnStream(stream)
    stream.close()
    return result
}

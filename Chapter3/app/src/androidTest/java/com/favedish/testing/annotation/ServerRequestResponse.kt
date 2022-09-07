package com.favedish.testing.annotation

@Target(AnnotationTarget.FUNCTION)
annotation class ServerRequestResponse(
    val requestResponseIds: Array<String>
)

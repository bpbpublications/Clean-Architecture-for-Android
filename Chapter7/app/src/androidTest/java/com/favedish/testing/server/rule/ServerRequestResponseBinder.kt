package com.favedish.testing.server.rule

import com.favedish.testing.annotation.ServerRequestResponse
import com.favedish.testing.rule.AnnotationProcessingRule.AnnotationProcessor
import com.favedish.testing.server.RequestResponseStore
import com.favedish.testing.server.ResponseContentStore

class ServerRequestResponseBinder(
    private val requestResponseStore: RequestResponseStore,
    private val responseContentStore: ResponseContentStore,
    private val annotation: ServerRequestResponse
) : AnnotationProcessor {
    override fun doBeforeTest() {
        annotation.requestResponseIds
            .map { requestResponseId ->
                requireNotNull(
                    requestResponseStore
                        .requestResponses[requestResponseId]
                ) {
                    "Request/Response ID $requestResponseId not found. " +
                        "Did you add it to the RequestResponseStore?"
                }
            }.forEach { requestResponse ->
                responseContentStore.addResponse(
                    requestResponse.request,
                    requestResponse.response
                )
            }
    }

    override fun doAfterTest() {
        responseContentStore.reset()
    }
}

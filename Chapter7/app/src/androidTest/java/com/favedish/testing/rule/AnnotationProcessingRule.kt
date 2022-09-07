package com.favedish.testing.rule

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AnnotationProcessingRule(
    private val annotationProcessorProviders:
        Map<Annotation, (Annotation) -> AnnotationProcessor>
) : TestRule {
    override fun apply(
        base: Statement,
        description: Description
    ): Statement = object : Statement() {
        override fun evaluate() {
            val annotationProcessors =
                description.annotations.mapNotNull { annotation ->
                    annotationProcessorProviders[annotation]?.run { invoke(annotation) }
                }

            annotationProcessors.forEach(AnnotationProcessor::doBeforeTest)
            base.evaluate()
            annotationProcessors.forEach(AnnotationProcessor::doAfterTest)
        }
    }

    interface AnnotationProcessor {
        fun doBeforeTest()
        fun doAfterTest()
    }
}

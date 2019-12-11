package com.cincinnatiai.grinchwalker.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GrinchTestCase(val testCaseName: String,
                                val updateServerWithResult: Boolean = false)
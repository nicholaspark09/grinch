package com.cincinnatiai.grinchwalker.model

data class GrinchConfig(
    val takeScreenShots: Boolean = false,
    val screenshotDirectory: String = "testFailures/",
    val resultUrl: String? = null
)